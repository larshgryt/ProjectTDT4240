package com.mygdx.game.handlers.collision;

import com.badlogic.gdx.math.Vector3;

public class CollisionBox {

    /* Collision boxes only have a collision mode. The position and size is retrieved from the
    collidable which implements it.
    The CollisionBox class was created to provide a standard logic for handling collision.
    Collidables may however choose to implement their own.
     */

    public enum CollisionMode{
        NEVER,      // Never collides with anything. Yields to nothing.
        ACTIVE,     // Collides with everything. Will yield to fixed, passive and active.
        PASSIVE,    // Collides with everything but other passives. Will yield to fixed and active.
        LITE,       // Collides with everything. Yields to everything.
        FIXED       // Collides with everything. Yields to nothing.
    }

    Collidable collidable;
    CollisionMode collisionMode;

    public CollisionBox(Collidable collidable, CollisionMode mode){
        this.collidable = collidable;
        this.collisionMode = mode;
    }

    /* Should return the impact point of collision for accurate collision handling and impulse
    response. May be implemented later. */
    public Vector3 impactPoint(CollisionBox other){
        // CALCULATE IMPACT POINT HERE
        return new Vector3(0, 0,0);
    }

    public CollisionMode getCollisionMode() {
        return collisionMode;
    }

    public void setCollisionMode(CollisionMode collisionMode) {
        this.collisionMode = collisionMode;
    }

    /* Checks whether two collision boxes may cause a collision response.*/
    public boolean isCollidable(Collidable other){
        CollisionMode otherMode = other.getCollisionBox().getCollisionMode();
        return(collisionMode != CollisionMode.NEVER && otherMode != CollisionMode.NEVER &&
                !(collisionMode == CollisionMode.PASSIVE && otherMode == CollisionMode.PASSIVE) &&
            collidable != other);
    }

    // Checks for collision using the separating axis theorem
    public boolean collidesWith(Collidable other){
        if(collidable == other){
            return false;
        }

        // Coordinates of the centers of rectangles A and B.
        Vector3 pA = new Vector3(collidable.getPosition().x + collidable.getWidth()/2,
                collidable.getPosition().y + collidable.getHeight()/2, 0);
        Vector3 pB = new Vector3(other.getPosition().x + other.getWidth()/2,
                other.getPosition().y + other.getHeight()/2, 0);

        // Unit vector of local x axis of rectangle A
        Vector3 xA = new Vector3((float)Math.cos(Math.toRadians(collidable.getAngle())),
                (float)Math.sin(Math.toRadians(collidable.getAngle())), 0);

        // Unit vector of local y axis of rectangle A
        Vector3 yA = new Vector3((float)Math.sin(Math.toRadians(collidable.getAngle())),
                (float)Math.cos(Math.toRadians(collidable.getAngle())), 0);

        // Unit vector of local x axis of rectangle B
        Vector3 xB = new Vector3((float)Math.cos(Math.toRadians(other.getAngle())),
                (float)Math.sin(Math.toRadians(other.getAngle())), 0);

        // Unit vector of local y axis of rectangle B
        Vector3 yB = new Vector3((float)Math.sin(Math.toRadians(other.getAngle())),
                (float)Math.cos(Math.toRadians(other.getAngle())), 0);

        // Half width of rectangle A along its local x-axis.
        Vector3 wA = new Vector3(xA.x, xA.y, 0).scl(collidable.getWidth()/2);

        // Half height of rectangle A along its local y-axis.
        Vector3 hA = new Vector3(yA.x, yA.y, 0).scl(collidable.getHeight()/2);

        // Half width of rectangle B along its local x-axis.
        Vector3 wB = new Vector3(xB.x, xB.y, 0).scl(other.getWidth()/2);

        // Half height of rectangle B along its local y-axis.
        Vector3 hB = new Vector3(yB.x, yB.y, 0).scl(other.getHeight()/2);

        Vector3[] unitVectors = new Vector3[4];
        unitVectors[0] = xA;
        unitVectors[1] = yA;
        unitVectors[2] = xB;
        unitVectors[3] = yB;

        Vector3 T = pB.cpy().sub(pA.cpy());

        for(Vector3 u: unitVectors){
            Vector3 L = u.cpy();

            //Projection of T onto L.
            float projT = Math.abs(T.x*L.x + T.y*L.y);

            // Projection of half of rectangle A onto L.
            float projA = Math.abs(wA.x*L.x + wA.y+L.y) + Math.abs(hA.x*L.x + hA.y*L.y);

            // Projection of hald of rectangle B onto L.
            float projB = Math.abs(wB.x*L.x + wB.y+L.y) + Math.abs(hB.x*L.x + hB.y*L.y);

            if(projT > projA + projB){
                return false;
            }
        }
        return true;
    }

    public void align(Collidable other){
        // Coordinates of the centers of rectangles A and B.
        Vector3 pA = new Vector3(collidable.getPosition().x + collidable.getWidth()/2,
                collidable.getPosition().y + collidable.getHeight()/2, 0);
        Vector3 pB = new Vector3(other.getPosition().x + other.getWidth()/2,
                other.getPosition().y + other.getHeight()/2, 0);

        // Unit vector of local x axis of rectangle A
        Vector3 xA = new Vector3((float)Math.cos(Math.toRadians(collidable.getAngle())),
                (float)Math.sin(Math.toRadians(collidable.getAngle())), 0);

        // Unit vector of local y axis of rectangle A
        Vector3 yA = new Vector3((float)Math.sin(Math.toRadians(collidable.getAngle())),
                (float)Math.cos(Math.toRadians(collidable.getAngle())), 0);

        // Unit vector of local x axis of rectangle B
        Vector3 xB = new Vector3((float)Math.cos(Math.toRadians(other.getAngle())),
                (float)Math.sin(Math.toRadians(other.getAngle())), 0);

        // Unit vector of local y axis of rectangle B
        Vector3 yB = new Vector3((float)Math.sin(Math.toRadians(other.getAngle())),
                (float)Math.cos(Math.toRadians(other.getAngle())), 0);

        // Half width of rectangle A along its local x-axis.
        Vector3 wA = new Vector3(xA.x, xA.y, 0).scl(collidable.getWidth()/2);

        // Half height of rectangle A along its local y-axis.
        Vector3 hA = new Vector3(yA.x, yA.y, 0).scl(collidable.getHeight()/2);

        // Half width of rectangle B along its local x-axis.
        Vector3 wB = new Vector3(xB.x, xB.y, 0).scl(other.getWidth()/2);

        // Half height of rectangle B along its local y-axis.
        Vector3 hB = new Vector3(yB.x, yB.y, 0).scl(other.getHeight()/2);

        int alpha1 = (int) Math.toDegrees(Math.acos((xA.x*xB.x + xA.y*xB.y)/(xA.len()*xB.len())));
        int alpha2 = (int) Math.toDegrees(Math.acos((xA.x*yB.x + xA.y*yB.y)/(xA.len()*yB.len())));
        int angle = Math.min(alpha1, alpha2);
        if(collidable.getAngle() < other.getAngle()){
            collidable.setAngle(collidable.getAngle() + angle/2);
            other.setAngle(other.getAngle() - angle/2);
        }
        else{
            collidable.setAngle(collidable.getAngle() - angle/2);
            other.setAngle(other.getAngle() + angle/2);
        }

    }

    public void alignTo(Collidable other){
        // Coordinates of the centers of rectangles A and B.
        Vector3 pA = new Vector3(collidable.getPosition().x + collidable.getWidth()/2,
                collidable.getPosition().y + collidable.getHeight()/2, 0);
        Vector3 pB = new Vector3(other.getPosition().x + other.getWidth()/2,
                other.getPosition().y + other.getHeight()/2, 0);

        // Unit vector of local x axis of rectangle A
        Vector3 xA = new Vector3((float)Math.cos(Math.toRadians(collidable.getAngle())),
                (float)Math.sin(Math.toRadians(collidable.getAngle())), 0);

        // Unit vector of local y axis of rectangle A
        Vector3 yA = new Vector3((float)Math.sin(Math.toRadians(collidable.getAngle())),
                (float)Math.cos(Math.toRadians(collidable.getAngle())), 0);

        // Unit vector of local x axis of rectangle B
        Vector3 xB = new Vector3((float)Math.cos(Math.toRadians(other.getAngle())),
                (float)Math.sin(Math.toRadians(other.getAngle())), 0);

        // Unit vector of local y axis of rectangle B
        Vector3 yB = new Vector3((float)Math.sin(Math.toRadians(other.getAngle())),
                (float)Math.cos(Math.toRadians(other.getAngle())), 0);

        // Half width of rectangle A along its local x-axis.
        Vector3 wA = new Vector3(xA.x, xA.y, 0).scl(collidable.getWidth()/2);

        // Half height of rectangle A along its local y-axis.
        Vector3 hA = new Vector3(yA.x, yA.y, 0).scl(collidable.getHeight()/2);

        // Half width of rectangle B along its local x-axis.
        Vector3 wB = new Vector3(xB.x, xB.y, 0).scl(other.getWidth()/2);

        // Half height of rectangle B along its local y-axis.
        Vector3 hB = new Vector3(yB.x, yB.y, 0).scl(other.getHeight()/2);

        int alpha1 = (int) Math.toDegrees(Math.acos((xA.x*xB.x + xA.y*xB.y)/(xA.len()*xB.len())));
        int alpha2 = (int) Math.toDegrees(Math.acos((xA.x*yB.x + xA.y*yB.y)/(xA.len()*yB.len())));
        if(collidable.getAngle() < other.getAngle()){
            collidable.setAngle(collidable.getAngle() + Math.min(alpha1, alpha2));
        }
        else{
            collidable.setAngle(collidable.getAngle() - Math.min(alpha1, alpha2));
        }

    }
    public boolean yieldsTo(Collidable other){
        if(isCollidable(other) && collisionMode != CollisionMode.FIXED){
            if(collisionMode == other.getCollisionBox().collisionMode ||
                    collisionMode == CollisionMode.LITE ||
                    other.getCollisionBox().collisionMode == CollisionMode.FIXED){
                return true;
            }
            else if(other.getCollisionBox().collisionMode != CollisionMode.LITE){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
}
