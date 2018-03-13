package com.mygdx.game.handlers.collision;

import com.badlogic.gdx.math.Vector3;

public class CollisionBox {

    public enum CollisionMode{
        NEVER,      // Never collides with anything.
        ACTIVE,     // Collides with everything. Will yield to fixed and passive.
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

    public boolean collidesWith(CollisionBox other){
        return false;
    }
    public Vector3 collisionPoint(CollisionBox other){
        return new Vector3(0, 0,0);
    }

    public CollisionMode getCollisionMode() {
        return collisionMode;
    }

    public void setCollisionMode(CollisionMode collisionMode) {
        this.collisionMode = collisionMode;
    }
    public boolean isCollidable(Collidable other){
        CollisionMode otherMode = other.getCollisionBox().getCollisionMode();
        return(collisionMode != CollisionMode.NEVER && otherMode != CollisionMode.NEVER &&
                !(collisionMode == CollisionMode.PASSIVE && otherMode == CollisionMode.PASSIVE));
    }

    // Checks for collision using the separating axis theorem
    public boolean collidesWith(Collidable other){
        Vector3 pA = new Vector3(collidable.getPosition().x + collidable.getWidth()/2,
                collidable.getPosition().y + collidable.getHeight()/2, 0);
        Vector3 xA = new Vector3(1, 0, 0).rotate(collidable.getAngle(), 1, 0, 0);
        Vector3 yA = new Vector3(0, 1, 0).rotate(collidable.getAngle(), 0, 1, 0);
        float wA = collidable.getWidth()/2;
        float hA = collidable.getHeight()/2;

        Vector3 pB = new Vector3(other.getPosition().x + other.getWidth()/2,
                other.getPosition().y + other.getHeight()/2, 0);
        Vector3 xB = new Vector3(1, 0, 0).rotate(other.getAngle(), 1, 0, 0);
        Vector3 yB = new Vector3(0, 1, 0).rotate(other.getAngle(), 0, 1, 0);
        float wB = other.getWidth()/2;
        float hB = other.getHeight()/2;

        Vector3[] axes = {xA, xB, yA, yB};

        for(Vector3 v: axes){
            Vector3 L = v.cpy();
            Vector3 T = pB.cpy().sub(pA);
            float projT = T.cpy().dot(L);
            float a = xA.cpy().scl(wA).dot(L);
            float b = yA.cpy().scl(hA).dot(L);
            float c = xB.cpy().scl(wB).dot(L);
            float d = yB.cpy().scl(hB).dot(L);
            if(projT > a + b + c + d){
                return false;
            }
        }
        return true;
    }
}
