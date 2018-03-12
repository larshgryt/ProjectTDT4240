package com.mygdx.game.components;

import com.badlogic.gdx.math.Vector3;

public class CollisionBox {

    public enum CollisionMode{
        NEVER,      // Never collides with anything.
        ACTIVE,     // Collides with everything. Will yield to fixed and passive.
        PASSIVE,    // Collides with everything but other passives. Will yield to fixed and active.
        LITE,       // Collides with everything. Yields to everything.
        FIXED       // Collides with everything. Yields to nothing.
    }
    public enum CollisionShape{
        RECTANGULAR, CIRCULAR
    }

    float width;
    float height;
    CollisionShape collisionShape;
    CollisionMode collisionMode;

    public CollisionBox(CollisionMode mode, CollisionShape shape, float width, float height){
        this.width = width;
        this.height = height;
        this.collisionMode = mode;
        this.collisionShape = shape;
    }

    public boolean collidesWith(CollisionBox other){
        return false;
    }
    public Vector3 collisionPoint(CollisionBox other){
        return new Vector3(0, 0,0);
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public CollisionShape getCollisionShape() {
        return collisionShape;
    }

    public void setCollisionShape(CollisionShape collisionShape) {
        this.collisionShape = collisionShape;
    }

    public CollisionMode getCollisionMode() {
        return collisionMode;
    }

    public void setCollisionMode(CollisionMode collisionMode) {
        this.collisionMode = collisionMode;
    }
}
