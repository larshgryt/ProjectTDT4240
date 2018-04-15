package com.mygdx.game.components.stage.stagecomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.mygdx.game.components.Component;
import com.mygdx.game.handlers.collision.Collidable;
import com.mygdx.game.handlers.collision.CollisionBox;

    /*
    A collidable rectangle component with a texture to be placed on a stage.
    Will have a fixed collision box, and will be static.
    */

public class StageComponent extends Component implements Collidable {

    private Texture texture;
    private TiledDrawable tiledDrawable;
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

    public StageComponent(TiledDrawable tiledDrawable, int width, int height){
        //Create a component which repeats inside area defined by width and height
        this.tiledDrawable = tiledDrawable;
        this.width = width;
        this.height = height;
        collisionBox = new CollisionBox(this, CollisionBox.CollisionMode.NEVER);
        friction = 0.95f;
    }

    public void setTexture(Texture texture){
        this.texture = texture;
        width = texture.getWidth();
        height = texture.getHeight();
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

        if(texture != null){
            sb.draw(texture, position.x, position.y, 0, 0, width, height,
                    1, 1, (float) Math.toRadians(angle),0, 0,
                    texture.getWidth(), texture.getHeight(), false, false);
        }
        if(tiledDrawable != null){
            tiledDrawable.draw(sb, position.x, position.y, width, height);
        }
    }

    @Override
    public void dispose() {

        if(texture != null){
            texture.dispose();
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
    public Vector3 getElasticity() {
        return new Vector3(0, 0, 0);
    }
    @Override
    public Vector3 getBounceThreshold() {
        return new Vector3(1000, 1000, 1000);
    }
    public float getFriction(){
        return friction;
    }
    public void setFriction(float friction){
        this.friction = friction;
    }
}
