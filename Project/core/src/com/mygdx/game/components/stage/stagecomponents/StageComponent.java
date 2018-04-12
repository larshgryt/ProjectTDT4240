package com.mygdx.game.components.stage.stagecomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;
import com.mygdx.game.handlers.collision.Collidable;
import com.mygdx.game.handlers.collision.CollisionBox;

    /*
    A collidable rectangle component with a texture to be placed on a stage.
    Will have a fixed collision box, and will be static.
    */

public class StageComponent extends Component implements Collidable {

    private Texture texture;
    private CollisionBox collisionBox;

    public StageComponent(int width, int height){
        //Create a white background texture
        Pixmap pixmap = new Pixmap(width,height, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        setTexture(new Texture(pixmap));
        pixmap.dispose();
        collisionBox = new CollisionBox(this, CollisionBox.CollisionMode.NEVER);
    }

    public StageComponent(Texture texture){
        setTexture(texture);
        collisionBox = new CollisionBox(this, CollisionBox.CollisionMode.NEVER);
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
}
