package com.mygdx.game.states;

import com.mygdx.game.handlers.input.InputHandler;
import com.mygdx.game.listeners.EventListener;
import com.mygdx.game.presenters.MenuPresenter;

import java.util.ArrayList;
import java.util.EventObject;


public class MenuState extends State {

    private MenuPresenter menuPresenter;

    public MenuState() {
        super();
        menuPresenter = new MenuPresenter();
        menuPresenter.addEventListener(new PlayGameListener());
        addPresenter(menuPresenter);
        setMusic("background.mp3");
    }

    private boolean isValid(String username){
        return(username.length() >= 3 && username.length() < 32);
    }
    private boolean isValid(ArrayList<String> usernames){
        if(usernames.size() < 2){
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public void handleInput() {

    }
    private class PlayGameListener extends EventListener{
        @Override
        public void notifyEvent(EventObject e) {
            if(e instanceof PlayEvent){
                ArrayList<String> usernames = ((PlayEvent) e).getUsernames();
                if(isValid(usernames)){
                    GameStateManager.getInstance().set(new GameState(usernames));
                }
                else{
                    menuPresenter.notifyError(e, "At least 2 players must join the game.");
                }
            }
            else if(e instanceof AddPlayerEvent){
                if(!((AddPlayerEvent) e).isChecked()){
                    ((AddPlayerEvent) e).check();
                    if(isValid(((AddPlayerEvent) e).getValue())){
                        menuPresenter.notifyEvent(e);
                    }
                    else{
                        menuPresenter.notifyError(e, "Invalid username was entered.");
                    }
                }
            }
        }

        @Override
        public void notifyError(EventObject e, String message) {

        }
    }
    public static class PlayEvent extends EventObject{
        private ArrayList<String> usernames;
        public PlayEvent(ArrayList<String> usernames){
            super(usernames);
            this.usernames = usernames;
        }
        public ArrayList<String> getUsernames(){
            return usernames;
        }
    }

    public static class AddPlayerEvent extends EventObject{
        private String username;
        private boolean checked;
        public AddPlayerEvent(String username){
            super(username);
            this.username = username;
            this.checked = false;
        }
        public String getValue(){
            return username;
        }
        public boolean isChecked(){
            return checked;
        }
        public void check(){
            checked = true;
        }
    }
}
