package com.mygdx.game.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Component {
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
