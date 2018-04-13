package com.mygdx.game.handlers.collision;


import com.badlogic.gdx.math.Vector3;

public interface Collidable {

    boolean collidesWith(Collidable other);
    BoundingBox getBoundingBox();
    CollisionBox getCollisionBox();
    void setPosition(Vector3 position);
    void setPosition(float x, float y);
    Vector3 getPosition();
    void setVelocity(Vector3 velocity);
    void setVelocity(float x, float y);
    Vector3 getVelocity();
    void setAcceleration(float x, float y);
    void setAcceleration(Vector3 acceleration);
    Vector3 getAcceleration();
    float getWidth();
    float getHeight();
    float getAngle();
    boolean bounces();
    Vector3 getElasticity();
    Vector3 getBounceThreshold();

}
