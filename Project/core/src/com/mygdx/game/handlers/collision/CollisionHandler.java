package com.mygdx.game.handlers.collision;

import com.mygdx.game.components.Component;
import com.mygdx.game.components.stage.Stage;
import com.mygdx.game.handlers.Handler;

import java.util.ArrayList;
import java.util.Collections;

public class CollisionHandler extends Handler {

    private static float BOUNCE_THRESHOLD = 98.1f;


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
                collidables.add((Collidable)c);
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

        // Check how many pixels the two collidables penetrate on each axis
        float xPenetration = Math.abs(Math.max(c1.getPosition().x, c2.getPosition().x)
                - Math.min(c1.getPosition().x+c1.getWidth(), c2.getPosition().x+c2.getWidth()));
        float yPenetration = Math.abs(Math.max(c1.getPosition().y, c2.getPosition().y)
                - Math.min(c1.getPosition().y+c1.getHeight(), c2.getPosition().y+c2.getHeight()));

        // Each collidables step value for backing off
        float x1 = 0;
        float y1 = 0;
        float x2 = 0;
        float y2 = 0;

        // Checks whether the collision is horizontal or vertical
        if(xPenetration < yPenetration){
            // Horizontal collision
            if(c1.getPosition().x < c2.getPosition().x){
                // c1 on the left, c2 on the right
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
            }
        }
        else{
            // Vertical collision
            if(c1.getPosition().y < c2.getPosition().y){
                // c1 on the bottom, c2 on the top
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
        while(c1.getBoundingBox().overlaps(c2.getBoundingBox())){
            c1.setPosition(c1.getPosition().x + x1, c1.getPosition().y + y1);
            c2.setPosition(c2.getPosition().x + x2, c2.getPosition().y + y2);
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
