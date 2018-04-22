package com.mygdx.game.handlers.input;

import com.mygdx.game.handlers.GameHandler;
import com.mygdx.game.states.GameState;
import com.mygdx.game.states.GameStateManager;

public class InputHandler {

    private static InputHandler instance = new InputHandler();

    public static InputHandler getInstance(){
        return instance;
    }


    public void processDragEvent(InputEvent e){
        if(GameStateManager.getInstance().getActiveState() instanceof GameState){
            GameHandler gameHandler = ((GameState) GameStateManager.getInstance().getActiveState()).getGameHandler();
            if(((String)e.getSource()).equalsIgnoreCase("drag")){
                gameHandler.playerAim(e.getX(), e.getY());
            }
        }
    }

    public void processButtonEvent(InputEvent e){
        System.out.println("Button clicked!");
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
            if(s.equalsIgnoreCase("release")){
                System.out.println("Released");
                gameHandler.stopMove();
                gameHandler.fireWeapon();
            }
        }
    }
}
