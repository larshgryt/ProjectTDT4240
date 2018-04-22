package com.mygdx.game.presenters;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GdxGame;
import com.mygdx.game.components.menucomponents.ImageButton;
import com.mygdx.game.handlers.input.InputEvent;
import com.mygdx.game.handlers.input.InputHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameStatePresenter extends Presenter {

    public GameStatePresenter(){
        super();
        addComponent(new RightButton());
        addComponent(new LeftButton());
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
