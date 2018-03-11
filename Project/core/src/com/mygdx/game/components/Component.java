package com.mygdx.game.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class Component {
    protected Vector3 position;
    protected float width;
    protected float height;
    public Component(){
        position = new Vector3(0, 0, 0);
        width = 0;
        height = 0;
    }
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
