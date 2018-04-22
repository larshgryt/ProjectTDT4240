package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ColoredField implements Sprite {

    Texture texture;

    public ColoredField(){
        setColor(Color.WHITE);
    }

    public ColoredField(Color color){
        setColor(color);
    }

    public void setColor(Color color){
        Pixmap pixmap = new Pixmap(1,1, Pixmap.Format.RGB888);
        pixmap.setColor(color);
        pixmap.fill();
        texture = (new Texture(pixmap));
        pixmap.dispose();
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb, float x, float y, float width, float height, float angle) {
        sb.draw(texture,x, y, 0, 0, (int)width, (int)height, 1, 1,
                angle, 0, 0, 1, 1, false, false);
    }

    @Override
    public void render(SpriteBatch sb, float x, float y, float width, float height, float angle, float originX, float originY) {
        sb.draw(texture,x, y, originX, originY, (int)width, (int)height, 1, 1,
                angle, 0, 0, 1, 1, false, false);
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
        return 1;
    }

    @Override
    public float getHeight() {
        return 1;
    }

    @Override
    public void setHorizontalFlip(boolean flip) {

    }

    @Override
    public void setVerticalFlip(boolean flip) {

    }
}
