package com.mygdx.game.components.stage.stagecomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.components.Component;
import com.mygdx.game.handlers.collision.Collidable;
import com.mygdx.game.handlers.collision.CollisionBox;
import com.mygdx.game.sprites.Animation;
import com.mygdx.game.sprites.Sprite;

    /*
    A collidable rectangle component with a texture to be placed on a stage.
    Will have a fixed collision box, and will be static.
    */

public class StageComponent extends Component implements Collidable {

    protected Sprite sprite;
    private CollisionBox collisionBox;
    protected float friction;       // A sliding actors' velocity is multiplied by the friction

    public StageComponent(int width, int height){
        super();

        //Create a white background texture
        Pixmap pixmap = new Pixmap(width,height, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        setTexture(new Texture(pixmap));
        pixmap.dispose();
        this.width = width;
        this.height = height;
        collisionBox = new CollisionBox(this, CollisionBox.CollisionMode.NEVER);
        friction = 0.95f;
    }

    public StageComponent(Texture texture){
        setTexture(texture);
        collisionBox = new CollisionBox(this, CollisionBox.CollisionMode.NEVER);
        friction = 0.95f;
    }

    public void setTexture(Texture texture){
        Animation animation = new Animation(100);
        animation.addFrame(texture);
        setSprite(animation);
    }
    public void setSprite(Sprite sprite){
        this.sprite = sprite;
        width = sprite.getWidth();
        height = sprite.getHeight();
    }

    // Sets whether the component is collidable (fixed) or just decorative.
    public void setCollidable(boolean collidable){
        if(collidable){
            collisionBox.setCollisionMode(CollisionBox.CollisionMode.FIXED);
        }
        else{
            collisionBox.setCollisionMode(CollisionBox.CollisionMode.NEVER);
        }
    }

    @Override
    public void render(SpriteBatch sb) {

        if(sprite != null){
            sprite.render(sb, position.x, position.y, width, height, angle);
        }
    }

    @Override
    public void dispose() {

        if(sprite != null){
            sprite.dispose();
        }

    }

    @Override
    public boolean collidesWith(Collidable other) {
        return getCollisionBox().collidesWith(other);
    }

    @Override
    public CollisionBox getCollisionBox() {
        return collisionBox;
    }

    @Override
    public void setVelocity(Vector3 velocity) {
        // StageComponent can not have velocity. May be added at a later point.
    }

    @Override
    public void setVelocity(float x, float y) {
        // StageComponent can not have velocity. May be added at a later point.
    }

    @Override
    public void setAcceleration(float x, float y) {
        // StageComponent can not have acceleration. May be added at a later point.
    }

    @Override
    public void setAcceleration(Vector3 acceleration) {
        // StageComponent can not have acceleration. May be added at a later point.
    }

    @Override
    public Vector3 getAcceleration() {
        return new Vector3(0,0,0);
    }

    @Override
    public Vector3 getVelocity() {
        return new Vector3(0, 0, 0);
    }

    @Override
    public boolean bounces() {
        return false;
    }

    @Override
    public float getElasticity() {
        return 0;
    }
    @Override
    public float getBounceThreshold() {
        return 1000;
    }
    public float getFriction(){
        return friction;
    }
    public void setFriction(float friction){
        this.friction = friction;
    }
}
