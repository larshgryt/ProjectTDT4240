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

    protected Vector3 velocity;
    protected Vector3 acceleration;
    protected CollisionBox collisionBox;
    protected boolean bounces;          // Whether the actor bounces on collision
    protected Vector3 elasticity;       // Velocity is multiplied by elasticity on collision bounce
    protected Vector3 bounceThreshold; // Minimum velocity required to bounce insted of stopping on collision

    // Enables an actor to keep several sprites.
    protected ArrayList<Sprite> sprites;

    // Contains the index of the active sprite.
    protected int currentSprite;

    // Whether the angle is static (false) or given by the velocity direction (true).
    protected boolean rotatesOnMovement;

    public Actor(){
        super();
        velocity = new Vector3(0, 0, 0);
        acceleration = new Vector3(0, 0,0);

        // Sets collision mode to uncollidable by default.
        collisionBox = new CollisionBox(this, CollisionBox.CollisionMode.NEVER);

        rotatesOnMovement = false;
        sprites = new ArrayList<Sprite>();
        currentSprite = 0;
        bounces = false;
        elasticity = new Vector3(0.8f, 0.5f, 0);
        bounceThreshold = new Vector3(30, 30,0);
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

    public void setVelocity(float x, float y){
        velocity.set(x, y, 0);
    }

    public Vector3 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector3 acceleration) {
        this.acceleration = acceleration;
    }

    public void setAcceleration(float x, float y){
        acceleration.set(x, y, 0);
    }

    // Returns the current active sprite of the actor.
    public Sprite getCurrentSprite() {
        if(sprites.isEmpty()){
            return null;
        }
        return sprites.get(currentSprite);
    }

    // Adds a sprite to the actors set of sprites.
    public void addSprite(Sprite sprite) {
        this.sprites.add(sprite);
    }
    public void removeSprite(float index){
        sprites.remove(index);
    }

    // Sets the active sprite to the one at the given index.
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
            setAngle((float) Math.acos(velocity.x/velocity.len()));
        }
        if(getCurrentSprite() != null){
            getCurrentSprite().setVerticalFlip(verticalFlip);
            getCurrentSprite().setHorizontalFlip(horizontalFlip);
            getCurrentSprite().update(dt);
        }
        boundingBox.update();
    }

    @Override
    public void render(SpriteBatch sb) {
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

    @Override
    public boolean bounces(){
        return bounces;
    }
    public void setElasticity(float x, float y){
        elasticity.set(x, y, 0);
    }
    @Override
    public Vector3 getElasticity(){
        return elasticity;
    }
    public void setBounceThreshold(float x, float y){
        bounceThreshold.set(x, y, 0);
    }
    @Override
    public Vector3 getBounceThreshold(){
        return bounceThreshold;
    }
}
