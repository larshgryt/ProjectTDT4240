package com.mygdx.game.presenters;

import com.mygdx.game.GdxGame;
import com.mygdx.game.components.menucomponents.ImageButton;
import com.mygdx.game.handlers.input.InputEvent;
import com.mygdx.game.handlers.input.InputHandler;
import com.mygdx.game.listeners.EventListener;
import java.util.EventObject;

public class GameStatePresenter extends Presenter {

    public GameStatePresenter(){
        super();
        addComponent(new RightButton());
        addComponent(new LeftButton());
    }

    private class RightButton extends ImageButton{
        public RightButton(){
            super("right-button.png");
            setPosition(GdxGame.WIDTH-getWidth(), (GdxGame.HEIGHT-getHeight())/2);
            addEventListener(new EventListener() {
                @Override
                public void notifyEvent(EventObject e) {
                    InputHandler.getInstance().processInputEvent(new InputEvent("right"));
                }

                @Override
                public void notifyError(EventObject e, String message) {

                }
            });
        }
    }

    private class LeftButton extends ImageButton{
        public LeftButton(){
            super("left-button.png");
            setPosition(0, (GdxGame.HEIGHT-getHeight())/2);
            addEventListener(new EventListener() {
                @Override
                public void notifyEvent(EventObject e) {
                    InputHandler.getInstance().processInputEvent(new InputEvent("left"));
                }

                @Override
                public void notifyError(EventObject e, String message) {

                }
            });
        }
    }
}
