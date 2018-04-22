package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Line implements Sprite {

    Texture texture;

    public Line(){
        //Create a white background texture
        Pixmap pixmap = new Pixmap(1,1, Pixmap.Format.RGB888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb, float x, float y, float width, float height, float angle) {
        sb.draw(texture, x, y, 0, 0, width, 1, 1, 1,
                angle,0, 0, 1, 1, false, false);
    }

    @Override
    public void render(SpriteBatch sb, float x, float y, float width, float height, float angle, float originX, float originY) {
        sb.draw(texture, x, y, 0, 0, width, 1, 1, 1,
                angle,0, 0, 1, 1, false, false);
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
        return 0;
    }

    @Override
    public float getHeight() {
        return 0;
    }

    @Override
    public void setHorizontalFlip(boolean flip) {

    }

    @Override
    public void setVerticalFlip(boolean flip) {

    }
}
