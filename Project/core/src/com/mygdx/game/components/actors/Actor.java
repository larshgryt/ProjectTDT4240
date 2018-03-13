package com.mygdx.game.components.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.handlers.collision.Collidable;
import com.mygdx.game.handlers.collision.CollisionBox;
import com.mygdx.game.components.Component;
import com.mygdx.game.sprites.Sprite;

public abstract class Actor extends Component implements Collidable{

    private Vector3 velocity;
    private Vector3 acceleration;
    private Sprite sprite;
    private CollisionBox collisionBox;


    private boolean rotatesOnMovement;

    public Actor(){
        super();
        velocity = new Vector3(0, 0, 0);
        acceleration = new Vector3(0, 0,0);
        sprite = null;
        collisionBox = new CollisionBox(this, CollisionBox.CollisionMode.NEVER);
        rotatesOnMovement = false;
    }

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

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
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
        if(sprite != null){
            sprite.update(dt);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if(sprite != null){
            sprite.render(sb);
        }
    }

    @Override
    public void dispose() {
        if(sprite != null){
            sprite.dispose();
        }
    }
}
