package com.mygdx.game.presenters;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GdxGame;
import com.mygdx.game.components.menucomponents.Grid;
import com.mygdx.game.components.menucomponents.ImageButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameStatePresenter extends Presenter {

    List<ImageButton> weaponTextures = new ArrayList<ImageButton>();
    Grid weaponGrid;
    ImageButton weaponButton;

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

        weaponButton.addActionListener(new WeaponButtonListener());
        bazookaButton.addActionListener(new BazookaButtonListener());
        menuButton.addActionListener(new MenuButtonListener());

        addComponent(menuButton);
        addComponent(weaponButton);
        addComponent(weaponGrid);
    }

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

}
