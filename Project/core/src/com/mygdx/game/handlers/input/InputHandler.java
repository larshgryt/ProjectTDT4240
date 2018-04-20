package com.mygdx.game.handlers.input;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.Component;
import com.mygdx.game.handlers.GameHandler;
import com.mygdx.game.handlers.Handler;
import com.mygdx.game.presenters.Presenter;
import com.mygdx.game.states.GameState;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.State;

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
        /*State activeState = GameStateManager.getInstance().getActiveState();
        if(activeState instanceof GameState) {
            GameHandler gameHandler = ((GameHandler)activeState.getMainHandler());
            for(Presenter presenter : activeState.getPresenters()) {
                for(Component component : presenter.getComponents()) {
                    System.out.println(new Vector2(screenX,screenY));
                    System.out.println(component.getBoundingBox().contains(new Vector2(screenX, screenY)));
                    if(component.getBoundingBox().contains(new Vector2(screenX, screenY))) {
                        gameHandler.playerMove(true);
                        System.out.println("Active player moved");
                    }
                }
            }
        }*/
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
