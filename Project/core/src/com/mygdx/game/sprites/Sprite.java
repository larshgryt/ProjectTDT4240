package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Sprite {

    void update(float dt);
    void render(SpriteBatch sb, float x, float y, float width, float height, float angle);
    void dispose();
    void reset();

}
