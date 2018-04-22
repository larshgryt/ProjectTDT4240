package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Image implements Sprite {

    Texture texture;
    float width;
    float height;

    public Image(String texturePath){
        texture = new Texture(texturePath);
        width = texture.getWidth();
        height = texture.getHeight();
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb, float x, float y, float width, float height, float angle) {
        sb.draw(texture, x, y, 0, 0, width, height, 1, 1, angle,
                0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }

    @Override
    public void render(SpriteBatch sb, float x, float y, float width, float height, float angle, float originX, float originY) {
        sb.draw(texture, x, y, originX, originY, width, height, 1, 1, angle,
                0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public void reset() {

    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public void setHorizontalFlip(boolean flip) {

    }

    @Override
    public void setVerticalFlip(boolean flip) {

    }
}
