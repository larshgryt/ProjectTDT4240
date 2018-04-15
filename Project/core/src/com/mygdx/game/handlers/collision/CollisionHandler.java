package com.mygdx.game.handlers.collision;

import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.stage.Stage;
import com.mygdx.game.components.stage.stagecomponents.StageComponent;
import com.mygdx.game.handlers.Handler;
import com.mygdx.game.states.State;

import java.util.ArrayList;
import java.util.Collections;

public class CollisionHandler extends Handler {

    private State state;

    public CollisionHandler(State state){
        super();
        this.state = state;
    }


    /* Uses sort and sweep algorithm to traverse through component array and check for possible
    collision. This is the broad phrase of collision detection. */
    public void checkForCollisions(ArrayList<Collidable> collidables){

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

    public void checkForCollisions(ArrayList<Component> components, Stage stage){
        ArrayList<Collidable> collidables = new ArrayList<Collidable>(stage.getStageComponents());
        for(Component c: components){
            if(c instanceof Collidable){
                collidables.add((Collidable) c);
            }
        }
        checkForCollisions(collidables);
    }

    // Narrow phase of collision detection between two collidables.
    private void handleCollision(Collidable c1, Collidable c2){
        if(c1.getCollisionBox().isCollidable(c2)){
            //Check whether the two collidables actually collide using their narrow collision boxes.
            if(c1.collidesWith(c2)){
                if(c1.getCollisionBox().yieldsTo(c2) || c2.getCollisionBox().yieldsTo(c1)){
                    collide(c1, c1.getCollisionBox().yieldsTo(c2), c2, c2.getCollisionBox().yieldsTo(c1));
                }
            }
        }
    }

    // Separates the two collidables so that they no longer touch
    public void collide(Collidable c1, boolean yields1, Collidable c2, boolean yields2){

        if(!yields1 && !yields2){
            return;
        }

        // Check how many pixels the two collidables bounding boxes penetrate on each axis
        float xPenetration = Math.abs(Math.max(c1.getBoundingBox().startX, c2.getBoundingBox().startX)
                - Math.min(c1.getBoundingBox().endX, c2.getBoundingBox().endX));
        float yPenetration = Math.abs(Math.max(c1.getBoundingBox().startY, c2.getBoundingBox().startY)
                - Math.min(c1.getBoundingBox().endY, c2.getBoundingBox().endY));
        if(yields1 && yields2){
            c1.getCollisionBox().align(c2);
        }
        else if(yields1){
            c1.getCollisionBox().alignTo(c2);
        }
        else{
            c2.getCollisionBox().alignTo(c1);
        }
        boolean b = true;
        int a1 = (int)Math.toDegrees(Math.atan(c1.getVelocity().y/c1.getVelocity().x));
        int a2 = (int)Math.toDegrees(Math.atan(c2.getVelocity().y/c2.getVelocity().x));
        System.out.println(a1 + " " + a2);
        if(((c1.getVelocity().len() > 0 && yields1) || (c2.getVelocity().len() > 0 && yields2))
                && a1 != a2){
            System.out.println("Collision mode 1");
            while(c1.collidesWith(c2)){
                if(b){
                    if(yields1){
                        c1.addPosition((c1.getVelocity().x / c1.getVelocity().len() * -1),
                                c1.getVelocity().y/c1.getVelocity().len() * -1);
                        System.out.println("cx1:"+c1.getPosition().x+" cy1:"+c1.getPosition().y);
                    }
                }
                else{
                    if(yields2){
                        c2.addPosition((c2.getVelocity().x / c2.getVelocity().len() * -1),
                                c2.getVelocity().y/c2.getVelocity().len() * -1);
                        System.out.println("cx2:"+c2.getPosition().x+" cy2:"+c2.getPosition().y);
                    }
                }
                b = !b;
            }
        }
            // Each collidables step value for backing off
            float x1 = 0;
            float y1 = 0;
            float x2 = 0;
            float y2 = 0;

            // Checks whether the collision is horizontal or vertical
            if(xPenetration < yPenetration){
                // Horizontal collision
                System.out.println("Horizontal collision");
                if(c1.getBoundingBox().startX < c2.getBoundingBox().startX){
                    // c1 on the left, c2 on the right
                    System.out.println(c1.toString()+" left, " + c2.toString() +" right");
                    if(yields1){
                        x1 = -1;
                        if(c1.bounces() && Math.abs(c1.getVelocity().y) > c1.getBounceThreshold().y){
                            c1.setVelocity(c1.getVelocity().x * -1 * c1.getElasticity().x, c1.getVelocity().y);
                        }
                        else{
                            c1.setVelocity(0, c1.getVelocity().y);
                        }
                    }
                    if(yields2){
                        x2 = 1;
                        if(c2.bounces() && Math.abs(c2.getVelocity().y) > c2.getBounceThreshold().y){
                            c2.setVelocity(c2.getVelocity().x * -1 * c2.getElasticity().x, c2.getVelocity().y);
                        }
                        else{
                            c2.setVelocity(0, c2.getVelocity().y);
                        }
                    }
                }
                else{
                    // c2 on the left, c1 on the right
                    System.out.println(c2.toString()+" left, " + c1.toString() +" right");
                    if(yields1){
                        x1 = 1;
                        if(c1.bounces() && Math.abs(c1.getVelocity().y) > c1.getBounceThreshold().y){
                            c1.setVelocity(c1.getVelocity().x * -1 * c1.getElasticity().x, c1.getVelocity().y);
                        }
                        else{
                            c1.setVelocity(0, c1.getVelocity().y);
                        }
                    }
                    if(yields2){
                        x2 = -1;
                        if(c2.bounces() && Math.abs(c2.getVelocity().y) > c2.getBounceThreshold().y){
                            c2.setVelocity(c2.getVelocity().x * -1 * c2.getElasticity().x, c2.getVelocity().y);
                        }
                        else{
                            c2.setVelocity(0, c2.getVelocity().y);
                        }
                    }
                    x1 = 1;
                    x2 = -1;
                }
            }
            else{
                // Vertical collision
                System.out.println("Vertical collision");
                if(c1.getBoundingBox().startY < c2.getBoundingBox().startY){
                    // c1 on the bottom, c2 on the top
                    System.out.println(c1.toString()+" bottom, " + c2.toString() +" top");
                    if(yields1){
                        y1 = -1;
                        if(c1.bounces() && Math.abs(c1.getVelocity().y) > c1.getBounceThreshold().y){
                            c1.setVelocity(c1.getVelocity().x, c1.getVelocity().y * -1 * c1.getElasticity().y);
                        }
                        else{
                            c1.setVelocity(c1.getVelocity().x, 0);
                        }
                    }
                    if(yields2){
                        y2 = 1;
                        if(c2.bounces() && Math.abs(c2.getVelocity().y) > c2.getBounceThreshold().y){
                            c2.setVelocity(c2.getVelocity().x, c2.getVelocity().y * -1 * c2.getElasticity().y);
                        }
                        else{
                            c2.setVelocity(c2.getVelocity().x, 0);
                        }
                    }
                }
                else{
                    // c2 on the bottom, c1 on the top
                    System.out.println(c2.toString()+" bottom, " + c1.toString() +" top");
                    if(yields1){
                        y1 = 1;
                        if(c1.bounces() && Math.abs(c1.getVelocity().y) > c1.getBounceThreshold().y){
                            c1.setVelocity(c1.getVelocity().x, c1.getVelocity().y * -1 * c1.getElasticity().y);
                        }
                        else{
                            c1.setVelocity(c1.getVelocity().x, 0);
                        }
                    }
                    if(yields2){
                        y2 = -1;
                        if(c2.bounces() && Math.abs(c2.getVelocity().y) > c2.getBounceThreshold().y){
                            c2.setVelocity(c2.getVelocity().x, c2.getVelocity().y * -1 * c2.getElasticity().y);
                        }
                        else{
                            c2.setVelocity(c2.getVelocity().x, 0);
                        }
                    }
                }
            }
            while(c1.collidesWith(c2)){
                System.out.println("Backing off.");
                System.out.println("c1x:"+c1.getPosition().x + " c1y:"+c1.getPosition().y);
                System.out.println("c2x:"+c2.getPosition().x + " c2y:"+c2.getPosition().y);
                if(b){
                    c1.addPosition(x1, y1);
                }
                else{
                    c2.addPosition(x2, y2);
                }
                b = !b;
            }
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
