package com.mygdx.game.presenters;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GdxGame;
import com.mygdx.game.components.actors.PlayerActor;
import com.mygdx.game.components.menucomponents.Grid;
import com.mygdx.game.components.menucomponents.ImageButton;
import com.mygdx.game.handlers.GameHandler;
import com.mygdx.game.handlers.input.InputEvent;
import com.mygdx.game.handlers.input.InputHandler;
import com.mygdx.game.listeners.EventListener;
import com.mygdx.game.states.GameState;
import com.mygdx.game.states.GameStateManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class GameStatePresenter extends Presenter {

    public GameStatePresenter(){
        super();
        addComponent(new RightButton());
        addComponent(new LeftButton());
        addComponent(new WeaponButton());
    }

    private class RightButton extends ImageButton{
        public RightButton(){
            super(new Texture("right-button.png"), "rightButton");
            setPosition(GdxGame.WIDTH-getWidth(), (GdxGame.HEIGHT-getHeight())/2);
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    InputHandler.getInstance().processInputEvent(new InputEvent("right"));
                }
            });
        }
    }

    private class LeftButton extends ImageButton{
        public LeftButton(){
            super(new Texture("left-button.png"), "leftButton");
            setPosition(0, (GdxGame.HEIGHT-getHeight())/2);
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    InputHandler.getInstance().processInputEvent(new InputEvent("left"));
                }
            });
        }
    }

    private class WeaponButton extends ImageButton{
        public WeaponButton(){
            super(new Texture("bazooka_temporary.png"), "weapon");
            setPosition(5, (GdxGame.HEIGHT - getHeight()));
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    InputHandler.getInstance().processInputEvent(new InputEvent("useWeapon"));
                }
            });
        }
    }
}
