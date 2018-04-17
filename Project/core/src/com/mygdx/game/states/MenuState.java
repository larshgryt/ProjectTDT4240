package com.mygdx.game.states;


import com.mygdx.game.components.Component;
import com.mygdx.game.listeners.EventListener;
import com.mygdx.game.presenters.MenuPresenter;
import com.mygdx.game.presenters.Presenter;

import java.util.EventObject;


public class MenuState extends State {

    private String username;
    MenuPresenter menuPresenter;

    public MenuState() {
        super();
        menuPresenter = new MenuPresenter();
        menuPresenter.addEventListener(new PlayGameListener());
        addPresenter(menuPresenter);
        username = "Guest";
    }

    private boolean isValid(String username){
        return(username.length() >= 3 && username.length() < 32);
    }

    @Override
    public void handleInput() {

    }

    private class PlayGameListener extends EventListener{
        @Override
        public void notifyEvent(EventObject e) {
            if(e instanceof PlayEvent){
                String newUsername = ((PlayEvent) e).getValue();
                if(isValid(newUsername)){
                    username = newUsername;
                    System.out.println("Username "+username+" was entered.");
                }
                else{
                    notifyError(e, "Invalid username was entered.");
                }
            }
        }

        @Override
        public void notifyError(EventObject e, String message) {
            if(e instanceof PlayEvent){
                menuPresenter.notifyError(e, message);
            }
        }
    }
    public static class PlayEvent extends EventObject{
        private String value;
        public PlayEvent(String string){
            super(string);
            this.value = string;
        }
        public String getValue(){
            return value;
        }
    }

}
