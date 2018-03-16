package com.mygdx.game.handlers.collision;

import com.mygdx.game.handlers.Handler;

import java.util.ArrayList;
import java.util.Collections;

public class CollisionHandler extends Handler {


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

    // Narrow phase of collision detection between two collidables.
    private void handleCollision(Collidable c1, Collidable c2){
        //Check whether the two collidables actually collide using their collision boxes.
        if(c1.collidesWith(c2)){
            // ADD COLLISION RESPONSE HERE
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
