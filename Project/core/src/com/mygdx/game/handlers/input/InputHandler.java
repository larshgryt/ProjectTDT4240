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
            GameHandler gameHandler = GameHandler.getInstance();
            if(((String)e.getSource()).equalsIgnoreCase("drag")){
                gameHandler.playerAim(e.getX(), e.getY());
            }
        }
    }

    public void processInputEvent(InputEvent e){
        String s = (String)e.getSource();
        if(GameStateManager.getInstance().getActiveState() instanceof GameState){
            GameHandler gameHandler = GameHandler.getInstance();
            if(s.equalsIgnoreCase("right")){
                gameHandler.playerMove(true);
            }
            if(s.equalsIgnoreCase("left")){
                gameHandler.playerMove(false);
            }
            if(s.equalsIgnoreCase("release")){
                gameHandler.stopMove();
                gameHandler.fireWeapon();
            }
        }
    }
}
