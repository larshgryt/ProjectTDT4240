package com.mygdx.game.handlers.input;


import com.mygdx.game.handlers.GameHandler;
import com.mygdx.game.handlers.Handler;

public class InputHandler extends Handler {
    GameHandler handler;

    public void moveLeft(){
        handler.playerMove(false);
    }

    public void moveRight(){
        handler.playerMove(true);
    }

    public void stopMove(){
        handler.stopMove();
    }


}
