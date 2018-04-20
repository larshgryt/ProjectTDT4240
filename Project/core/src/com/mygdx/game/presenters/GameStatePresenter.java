package com.mygdx.game.presenters;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GdxGame;
import com.mygdx.game.components.menucomponents.Grid;
import com.mygdx.game.components.menucomponents.ImageButton;
import com.mygdx.game.handlers.input.InputEvent;
import com.mygdx.game.handlers.input.InputHandler;
import com.mygdx.game.states.GameState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameStatePresenter extends Presenter {

    private List<ImageButton> weaponTextures = new ArrayList<ImageButton>();
    private Grid weaponGrid;
    private ImageButton weaponButton;

    public GameStatePresenter(){
        super();
        ImageButton bazookaButton = new ImageButton(new Texture("bazooka_temporary.png"),"bazooka");
        weaponTextures.add(bazookaButton);

        weaponGrid = new Grid(weaponTextures);
        weaponGrid.setPosition(weaponGrid.getWidth(), GdxGame.HEIGHT - weaponGrid.getHeight());
        weaponGrid.positionGrid(false);

        weaponButton = new ImageButton(new Texture("bazooka_temporary.png"), "bazooka");
        weaponButton.setPosition(5, (GdxGame.HEIGHT - weaponButton.getHeight()));


        ImageButton menuButton = new ImageButton(new Texture("menuButton.png"), "menuButton");
        menuButton.setPosition(GdxGame.WIDTH-menuButton.getWidth()-5,GdxGame.HEIGHT-5);

        //Movement control buttons
        ImageButton rightButton = new ImageButton(new Texture("right-button.png"), "rightButton");
        ImageButton leftButton = new ImageButton(new Texture("left-button.png"), "leftButton");
        rightButton.setPosition(GdxGame.WIDTH-rightButton.getWidth(), (GdxGame.HEIGHT-leftButton.getHeight())/2);
        leftButton.setPosition(0, (GdxGame.HEIGHT-leftButton.getHeight())/2);

/*        weaponButton.addActionListener(new WeaponButtonListener());
        bazookaButton.addActionListener(new BazookaButtonListener());
        menuButton.addActionListener(new MenuButtonListener());*/
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                InputHandler.getInstance().processInputEvent(new InputEvent("right"));
            }
        });
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                InputHandler.getInstance().processInputEvent(new InputEvent("left"));
            }
        });

        addComponent(rightButton);
        addComponent(leftButton);
        addComponent(menuButton);
        addComponent(weaponButton);
        addComponent(weaponGrid);
    }
/*
    private class WeaponButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            setVisible(!isVisible());
        }

    }
    private class BazookaButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            weaponButton.setTexture(new Texture("bazooka_temporary.png"));
            setVisible(!isVisible());
            //change weapon logic here
        }

    }

    private class MenuButtonListener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        }

    }

    private class rightButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent){

        }
    }*/

}
