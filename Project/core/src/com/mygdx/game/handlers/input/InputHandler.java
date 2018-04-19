package com.mygdx.game.handlers.input;


import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.handlers.GameHandler;
import com.mygdx.game.handlers.Handler;
import com.mygdx.game.states.GameState;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.State;

public class InputHandler extends Handler implements InputProcessor{

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        State activeState = GameStateManager.getInstance().getActiveState();
        if(activeState instanceof GameState) {
            ((GameHandler)activeState.getMainHandler()).playerMove(true);
            System.out.println("Active player moved");
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
