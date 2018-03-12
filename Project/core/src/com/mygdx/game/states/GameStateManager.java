package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

    private static GameStateManager instance = new GameStateManager();

    private Stack<State> states;

    private GameStateManager(){
        states = new Stack<State>();
    }

    public static GameStateManager getInstance(){
        return instance;
    }

    public void push(State state){
        states.push(state);
    }

    public void set(State state){
        states.pop();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
    public void dispose(){
        for(State s: states){
            s.dispose();
        }
    }

}

