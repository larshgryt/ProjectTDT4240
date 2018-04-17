package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;

/**
 * Created by An on 16.04.2018.
 */

public class Tiles implements Sprite {

    private TiledDrawable td;
    private int width;
    private int height;

    public Tiles(Texture texture, int width, int height){
        this.td = new TiledDrawable(new TextureRegion(texture));
        this.width = width;
        this.height = height;
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
