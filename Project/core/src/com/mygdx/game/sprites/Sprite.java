package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Sprite {

    void update(float dt);
    void render(SpriteBatch sb, float x, float y, float width, float height, float angle);
    void render(SpriteBatch sb, float x, float y, float width, float height, float angle, float originX, float originY);
    void dispose();
    void reset();
    float getWidth();
    float getHeight();
    void setHorizontalFlip(boolean flip);
    void setVerticalFlip(boolean flip);

}
