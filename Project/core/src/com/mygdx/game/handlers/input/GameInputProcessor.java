package com.mygdx.game.handlers.input;

import com.badlogic.gdx.InputProcessor;

public class GameInputProcessor implements InputProcessor {

    public boolean keyDown (int keycode) {
        return false;
    }

    public boolean keyUp (int keycode) {
        return false;
    }

    public boolean keyTyped (char character) {
        return false;
    }

    public boolean touchDown (int x, int y, int pointer, int button) {
        // TODO: Write conditionals for button ranges and call the corresponding function in InputHandler
        return true;
    }

    public boolean touchUp (int x, int y, int pointer, int button) {
        //handler.stopMove();
        return true;
    }

    public boolean touchDragged (int x, int y, int pointer) {
        // Aiming perhaps?
        return false;
    }

    public boolean mouseMoved (int x, int y) {
        return false;
    }

    public boolean scrolled (int amount) {
        return false;
    }
}
