package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Sprite {

    void update(float dt);
    void render(SpriteBatch sb);
    void dispose();

}
