package com.mygdx.game.components.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.handlers.collision.Collidable;
import com.mygdx.game.handlers.collision.CollisionBox;
import com.mygdx.game.components.Component;
import com.mygdx.game.sprites.Sprite;

import java.util.ArrayList;

public abstract class Actor extends Component implements Collidable{

    /* Extends component, but in addition to a position it also has velocity, acceleration, a
    sprite and a collisionBox.
     */

    private Vector3 velocity;
    private Vector3 acceleration;
    private CollisionBox collisionBox;

    // Enables an actor to keep several animations.
    private ArrayList<Sprite> sprites;

    // Contains the index of the active animation.
    private int currentSprite;


    private boolean rotatesOnMovement;

    public Actor(){
        super();
        velocity = new Vector3(0, 0, 0);
        acceleration = new Vector3(0, 0,0);
        collisionBox = new CollisionBox(this, CollisionBox.CollisionMode.NEVER);
        rotatesOnMovement = false;
        sprites = new ArrayList<Sprite>();
        currentSprite = 0;
    }

    /* Whether the actor rotates in direction of the velocity. */
    public void setRotatesOnMovement(boolean r){
        this.rotatesOnMovement = r;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector3 velocity) {
        this.velocity = velocity;
    }

    public Vector3 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector3 acceleration) {
        this.acceleration = acceleration;
    }

    public Sprite getCurrentSprite() {
        if(sprites.isEmpty()){
            return null;
        }
        return sprites.get(currentSprite);
    }

    public void addSprite(Sprite sprite) {
        this.sprites.add(sprite);
    }
    public void removeSprite(float index){
        sprites.remove(index);
    }
    public void setSprite(int index){
        if(index < sprites.size()){
            currentSprite = index;
            getCurrentSprite().reset();
        }
    }

    public CollisionBox getCollisionBox() {
        return collisionBox;
    }

    public void setCollisionBox(CollisionBox collisionBox) {
        this.collisionBox = collisionBox;
    }



    @Override
    public void update(float dt) {
        position.add(velocity.x*dt, velocity.y*dt, velocity.z*dt);
        velocity.add(acceleration.x*dt, acceleration.y*dt, acceleration.z*dt);
        if(rotatesOnMovement){
            setAngle((float) Math.toDegrees((Math.acos(velocity.x/velocity.len()))));
        }
        if(getCurrentSprite() != null){
            getCurrentSprite().update(dt);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if(getCurrentSprite() != null){
            getCurrentSprite().render(sb, position.x, position.y, width, height, angle);
        }
    }

    public void render(SpriteBatch sb, float width, float height, float angle){
        if(getCurrentSprite() != null){
            getCurrentSprite().render(sb, position.x, position.y, width, height, angle);
        }
    }

    @Override
    public void dispose() {
        for(Sprite sprite: sprites){
            sprite.dispose();
        }
    }

    @Override
    public boolean collidesWith(Collidable other) {
        return getCollisionBox().collidesWith(other);
    }
}
