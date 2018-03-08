package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by agava on 08.03.2018.
 */

public abstract class State {
    protected GameStateManager gsm;

    public State(GameStateManager gsm){
        this.gsm = gsm;
    }

    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
