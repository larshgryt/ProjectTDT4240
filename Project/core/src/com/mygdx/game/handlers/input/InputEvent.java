package com.mygdx.game.handlers.input;

import java.util.EventObject;

public class InputEvent extends EventObject {
    float x;
    float y;
    public InputEvent(String s) {
        super(s);
        x = 0;
        y = 0;
    }
    public InputEvent(float x, float y, String message){
        super(message);
        this.x = x;
        this.y = y;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
}
