package com.mygdx.game.handlers.collision;


import com.badlogic.gdx.math.Vector3;

public interface Collidable {

    boolean collidesWith(Collidable other);
    BoundingBox getBoundingBox();
    CollisionBox getCollisionBox();
    Vector3 getPosition();
    float getWidth();
    float getHeight();
    float getAngle();

}
