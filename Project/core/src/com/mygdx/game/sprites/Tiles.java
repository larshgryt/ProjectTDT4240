package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;

public class Tiles implements Sprite {

    private TiledDrawable td;
    private int width;
    private int height;

    public Tiles(String texturePath, int width, int height){
        Texture t = new Texture(texturePath);
        this.td = new TiledDrawable(new TextureRegion(t));
        if(width == 0){
            this.width = t.getWidth();
        }
        else{
            this.width = width;
        }
        if(height == 0){
            this.height = t.getHeight();
        }
        else{
            this.height = height;
        }
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb, float x, float y, float width, float height, float angle) {
        td.draw(sb, x, y, width, height);
    }

    @Override
    public void render(SpriteBatch sb, float x, float y, float width, float height, float angle, float originX, float originY) {
        td.draw(sb, x, y, width, height);
    }

    @Override
    public void dispose() {
        td.getRegion().getTexture().dispose();
    }

    @Override
    public void reset() {

    }

    @Override
    public float getWidth() {
        return this.width;
    }

    @Override
    public float getHeight() {
        return this.height;
    }

    public float getTileWidth() {
        return td.getMinWidth();
    }

    public float getTileHeight() {
        return td.getMinHeight();
    }

    @Override
    public void setHorizontalFlip(boolean flip) {

    }

    @Override
    public void setVerticalFlip(boolean flip) {

    }
}
