package com.mygdx.game.handlers.collision;

import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.actors.projectiles.Projectile;
import com.mygdx.game.components.stage.Stage;
import com.mygdx.game.states.State;

import java.util.ArrayList;
import java.util.Collections;

public class CollisionHandler {

    public static CollisionHandler instance = new CollisionHandler();

    private State state;

    private CollisionHandler(){
        this.state = null;
    }

    public static CollisionHandler getInstance(){
        return instance;
    }


    /* Uses sort and sweep algorithm to traverse through component array and check for possible
    collision. This is the broad phrase of collision detection. */
    public void checkForCollisions(State state, ArrayList<Collidable> collidables){

        this.state = state;

        /* These are all the x-positions of edges of bounding boxes. Used to check for overlapping
        bounding boxes on the x-axis, and sort them for a sort-and-sweep traversal.*/

        ArrayList<SortingPoint> xPoints = new ArrayList<SortingPoint>();
        for(Collidable c: collidables){
            xPoints.add(new SortingPoint(c, true, true));
            xPoints.add(new SortingPoint(c, true, false));
        }
        Collections.sort(xPoints); // Sort on lowest x-value to highest

        ArrayList<Collidable> activeIntervals = new ArrayList<Collidable>();

        /* When the beginning of a bounding box interval is reached, add that collidable to the
        active intervals array. When the end of an interval is reached, remove collidable from the
        array.
         */
        for(SortingPoint sp: xPoints){
            if(sp.start){
                activeIntervals.add(sp.collidable);
            }
            else{
                activeIntervals.remove(sp.collidable);
            }

            /* Check for possible collisions between all active intervals */
            for(int i = 0; i < activeIntervals.size(); i++){
                Collidable c1 = activeIntervals.get(i);
                for(int k = i; k < activeIntervals.size(); k++){
                    Collidable c2 = activeIntervals.get(k);

                    /* If the bounding boxes overlaps, enter the narrow phase of collision
                    detection.
                     */
                    if(c1.getBoundingBox().overlaps(c2.getBoundingBox())){
                        handleCollision(c1, c2);
                    }
                }
            }
        }

    }

    public void checkForCollisions(State state, ArrayList<Component> components, Stage stage){
        ArrayList<Collidable> collidables = new ArrayList<Collidable>(stage.getStageComponents());
        for(Component c: components){
            if(c instanceof Collidable){
                collidables.add((Collidable) c);
            }
        }
        checkForCollisions(state, collidables);
    }

    // Narrow phase of collision detection between two collidables.
    private void handleCollision(Collidable c1, Collidable c2){
        if(c1.getCollisionBox().isCollidable(c2)){
            //Check whether the two collidables actually collide using their narrow collision boxes.
            if(c1.collidesWith(c2)){
                if(c1 instanceof Projectile && c2 instanceof Projectile){
                    ((Projectile)c1).hit(state, (Projectile)c2);
                }
                else if(c1 instanceof Projectile){
                    ((Projectile)c1).hit(state, c2);
                }
                else if(c2 instanceof Projectile){
                    ((Projectile)c2).hit(state, c1);
                }
                else if(c1.getCollisionBox().yieldsTo(c2) || c2.getCollisionBox().yieldsTo(c1)){
                    collide(c1, c2);
                }
            }
        }
    }


    public void collide(Collidable c1, Collidable c2){

        boolean yields1 = c1.getCollisionBox().yieldsTo(c2);
        boolean yields2 = c2.getCollisionBox().yieldsTo(c1);

        if(!yields1 && !yields2){
            return;
        }

        // Rotate the collidables so that they are aligned.
        if(yields1 && yields2){
            c1.getCollisionBox().align(c2);
        }
        else if(yields1){
            c1.getCollisionBox().alignTo(c2);
        }
        else{
            c2.getCollisionBox().alignTo(c1);
        }
        impact(c1, c2);
        separate(c1, c2);
    }

    // Changes velocities at impact.
    public void impact(Collidable c1, Collidable c2){

        boolean yields1 = c1.getCollisionBox().yieldsTo(c2);
        boolean yields2 = c2.getCollisionBox().yieldsTo(c1);

        Vector3 v1 = new Vector3(0, 0, 0);  // Unit vector for velocity change
        Vector3 v2 = new Vector3(0, 0, 0);  // Unit vector for velocity change

        float angle1 = c1.getAngle();   // The collidables original angles
        float angle2 = c2.getAngle();   // the collidables original angles

        float angle;
        if(yields2){
            angle = c1.getAngle();
        }
        else{
            angle = c2.getAngle();
        }

        // Rotate the collidables positions around the origin
        float px1 = c1.getPosition().x;
        float py1 = c1.getPosition().y;
        float px2 = c2.getPosition().x;
        float py2 = c2.getPosition().y;

        double theta = Math.toRadians(-1 *angle);

        float x1 = px1 * (float)Math.cos(theta) - py1 * (float)Math.sin(theta);
        float y1 = px1 * (float)Math.sin(theta) + py1 * (float)Math.cos(theta);
        float x2 = px2 * (float)Math.cos(theta) - py2 * (float)Math.sin(theta);
        float y2 = px2 * (float)Math.sin(theta) + py2 * (float)Math.cos(theta);

        c1.setPosition(x1, y1);
        c2.setPosition(x2, y2);

        // Rotate the rectangles around their centers.
        c1.setAngle(angle1 - angle);
        c2.setAngle(angle2 - angle);

        // Update the bounding boxes accordingly.
        c1.getBoundingBox().update();
        c2.getBoundingBox().update();

        if(c1.getBoundingBox().overlaps(c2.getBoundingBox())) {

            // Calculate the amount of overlapping pixels on each axis.
            BoundingBox intersection = c1.getBoundingBox().intersection(c2.getBoundingBox());
            float xOverlap = intersection.endX - intersection.startX;
            float yOverlap = intersection.endY - intersection.startY;

            if (xOverlap < yOverlap) {
                // Horozontal collision
                if (c1.getBoundingBox().startX < c2.getBoundingBox().startX) {
                    // c1 on the left, c2 on the right
                    if (yields1 || (!yields1 && !yields2)) {
                        v1.x = -1;
                    }
                    if (yields2 || (!yields1 && !yields2)) {
                        v2.x = 1;
                    }
                } else {
                    // c2 on the left, c1 on the right
                    if (yields1 || (!yields1 && !yields2)) {
                        v1.x = 1;
                    }
                    if (yields2 || (!yields1 && !yields2)) {
                        v2.x = -1;
                    }
                }
            } else {
                // Vertical collision
                if (c1.getBoundingBox().startY < c2.getBoundingBox().startY) {
                    // c1 on the bottom, c2 on the top
                    if (yields1 || (!yields1 && !yields2)) {
                        v1.y = -1;
                    }
                    if (yields2 || (!yields1 && !yields2)) {
                        v2.y = 1;
                    }
                } else {
                    // c2 on the bottom, c1 on the top
                    if (yields1 || (!yields1 && !yields2)) {
                        v1.y = 1;
                    }
                    if (yields2 || (!yields1 && !yields2)) {
                        v2.y = -1;
                    }
                }
            }
        }

        v1 = v1.rotate(Vector3.Z, angle);
        v2 = v2.rotate(Vector3.Z, angle);

        Vector3 A = c1.getVelocity().cpy();
        Vector3 B = c2.getVelocity().cpy();

        c1.setVelocity(A.sub(v1.cpy().scl(A.dot(v1) * 2)).scl(c1.getElasticity()));
        c2.setVelocity(B.sub(v2.cpy().scl(B.dot(v2) * 2)).scl(c2.getElasticity()));

        c1.setPosition(px1, py1);
        c1.setAngle(angle1);
        c2.setPosition(px2, py2);
        c2.setAngle(angle2);
        c1.getBoundingBox().update();
        c2.getBoundingBox().update();
    }

    // Separates the two collidables so that they no longer touch
    public void separate(Collidable c1, Collidable c2){

        boolean yields1 = c1.getCollisionBox().yieldsTo(c2);
        boolean yields2 = c2.getCollisionBox().yieldsTo(c1);

        Vector3 m1 = new Vector3(0, 0, 0);  // Movement vector for separation
        Vector3 m2 = new Vector3(0, 0, 0);  // Movement vector for separation

        float angle1 = c1.getAngle();   // The collidables original angles
        float angle2 = c2.getAngle();   // the collidables original angles

        // Align the two collidables along the x-axis and y-axis
        float angle = c1.getAngle();

        // Rotate the collidables positions around the origin
        float px1 = c1.getPosition().x;
        float py1 = c1.getPosition().y;
        float px2 = c2.getPosition().x;
        float py2 = c2.getPosition().y;

        double theta = Math.toRadians(-1 *angle);

        float x1 = px1 * (float)Math.cos(theta) - py1 * (float)Math.sin(theta);
        float y1 = px1 * (float)Math.sin(theta) + py1 * (float)Math.cos(theta);
        float x2 = px2 * (float)Math.cos(theta) - py2 * (float)Math.sin(theta);
        float y2 = px2 * (float)Math.sin(theta) + py2 * (float)Math.cos(theta);

        c1.setPosition(x1, y1);
        c2.setPosition(x2, y2);

        // Rotate the rectangles around their centers.
        c1.setAngle(angle1 - angle);
        c2.setAngle(angle1 - angle);

        // Update the bounding boxes accordingly.
        c1.getBoundingBox().update();
        c2.getBoundingBox().update();

        if(c1.getBoundingBox().overlaps(c2.getBoundingBox())) {
            // The movement vectors for the separation
            float sx1 = 0;   // Step unit for backing off
            float sy1 = 0;   // Step unit for backing off
            float sx2 = 0;   // Step unit for backing off
            float sy2 = 0;   // Step unit for backing off

            // Calculate the amount of overlapping pixels on each axis.
            BoundingBox intersection = c1.getBoundingBox().intersection(c2.getBoundingBox());
            float xOverlap = intersection.endX - intersection.startX;
            float yOverlap = intersection.endY - intersection.startY;

            if (xOverlap < yOverlap) {
                // Horozontal collision
                if(c1.getBoundingBox().startX < c2.getBoundingBox().startX){
                    // c1 on the left, c2 on the right
                    if(yields1 || (!yields1 && !yields2)){
                        m1.x = -1;
                        sx1 = -1;
                    }
                    if(yields2 || (!yields1 && !yields2)){
                        m2.x = 1;
                        sx2 = 1;
                    }
                }
                else{
                    // c2 on the left, c1 on the right
                    if(yields1 || (!yields1 && !yields2)){
                        m1.x = 1;
                        sx1 = 1;
                    }
                    if(yields2 || (!yields1 && !yields2)){
                        m2.x = -1;
                        sx2 = -1;
                    }
                }
            }
            else{
                // Vertical collision
                if(c1.getBoundingBox().startY < c2.getBoundingBox().startY){
                    // c1 on the bottom, c2 on the top
                    if(yields1 || (!yields1 && !yields2)){
                        m1.y = -1;
                        sy1 = -1;
                    }
                    if(yields2 || (!yields1 && !yields2)){
                        m2.y = 1;
                        sy2 = 1;
                    }
                }
                else{
                    // c2 on the bottom, c1 on the top
                    if(yields1 || (!yields1 && !yields2)){
                        m1.y = 1;
                        sy1 = 1;
                    }
                    if(yields2 || (!yields1 && !yields2)){
                        m2.y = -1;
                        sy2 = -1;
                    }
                }
            }
            boolean turn = true;
            boolean overlaps = c1.getBoundingBox().overlaps(c2.getBoundingBox());
            while(overlaps){
                if(turn){
                    // c1 yields
                    c1.setPosition(x1 + m1.x, y1 + m1.y);
                }
                else{
                    // c2 yields
                    c2.setPosition(x2 + m2.x, y2 + m2.y);
                }
                c1.getBoundingBox().update();
                c2.getBoundingBox().update();
                overlaps = c1.getBoundingBox().overlaps(c2.getBoundingBox());
                if(overlaps){
                    m1.set(m1.x + sx1, m1.y + sy1, 0);
                    m2.set(m2.x + sx2, m2.y + sy2, 0);
                    turn = !turn;
                }
            }
        }
        // Rotate the movement vectors by the given angle.
        Vector3 movement1 = m1.cpy().rotate(Vector3.Z, angle);

        // Rotate the movement vectors by the given angle.
        Vector3 movement2 = m2.cpy().rotate(Vector3.Z, angle);

        // Rotate the collidables to their original angle.
        c1.setAngle(angle1);
        c2.setAngle(angle2);

        // Move the collidables to their original locations and add the separating movement vectors.
        c1.setPosition(px1 + movement1.x, py1 + movement1.y);
        c2.setPosition(px2 + movement2.x, py2 + movement2.y);
    }

    private float angle(Vector3 v1, Vector3 v2){
        return((v1.x*v2.x + v1.y*v2.y)/(v1.len()*v2.len()));
    }
    private float proj(Vector3 v1, Vector3 v2){
        return Math.abs(v1.x*v2.x + v1.y*v2.y);
    }



    /* Helping class to sort the array of collidables */
    private class SortingPoint implements Comparable{
        Collidable collidable;
        float value;    // Coordinate value of point.
        boolean x;      // Whether the value is on the x-axis.
        boolean start;  // Whether the point is the beginning (true) or end (false) of interval.

        SortingPoint(Collidable collidable, boolean x, boolean start){
            this.collidable = collidable;
            this.x = x;
            this.start = start;
            collidable.getBoundingBox().update();
            if(start && x){
                value = collidable.getBoundingBox().startX;
            }
            else if(x){
                value = collidable.getBoundingBox().endX;
            }
            else if(start){
                value = collidable.getBoundingBox().startY;
            }
            else{
                value = collidable.getBoundingBox().endY;
            }
        }

        @Override
        public int compareTo(Object o) {
            return (int) (((SortingPoint)o).value - value);
        }
    }


}
