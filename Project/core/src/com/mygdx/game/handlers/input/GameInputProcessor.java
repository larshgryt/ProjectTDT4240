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
        InputHandler.getInstance().processInputEvent(new InputEvent("release"));
        return true;
    }

    public boolean touchDragged (int x, int y, int pointer) {
        InputHandler.getInstance().processDragEvent(new InputEvent(x, y, "drag"));
        return true;
    }

    public boolean mouseMoved (int x, int y) {
        return false;
    }

    public boolean scrolled (int amount) {
        return false;
    }
}
