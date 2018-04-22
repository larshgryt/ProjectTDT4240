package com.mygdx.game.handlers.input;


import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.handlers.GameHandler;
import com.mygdx.game.handlers.Handler;
import com.mygdx.game.states.GameState;
import com.mygdx.game.states.GameStateManager;

public class InputHandler extends Handler implements InputProcessor{

    private static InputHandler instance = new InputHandler();

    private InputHandler(){
        super();
    }

    public static InputHandler getInstance(){
        return instance;
    }

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
        return false;
    }

    public void processInputEvent(InputEvent e){
        String s = (String)e.getSource();
        if(GameStateManager.getInstance().getActiveState() instanceof GameState){
            GameState gameState = (GameState)GameStateManager.getInstance().getActiveState();
            GameHandler gameHandler = gameState.getGameHandler();
            if(s.equalsIgnoreCase("right")){
                gameHandler.playerMove(true);
            }
            if(s.equalsIgnoreCase("left")){
                gameHandler.playerMove(false);
            }
            if(s.equalsIgnoreCase("useWeapon")){
                //TODO: Add functionality for shooting
            }
        }
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(GameStateManager.getInstance().getActiveState() instanceof GameState){
            GameState gameState = (GameState)GameStateManager.getInstance().getActiveState();
            GameHandler gameHandler = gameState.getGameHandler();
            if (gameHandler.getActivePlayer().isMoving()){
                gameHandler.getActivePlayer().setMoving(false);
            }
        }
        return true;
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
