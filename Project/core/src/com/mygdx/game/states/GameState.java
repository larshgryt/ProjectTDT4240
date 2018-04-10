package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GdxGame;
import com.mygdx.game.components.menucomponents.Button;
import com.mygdx.game.components.menucomponents.Grid;
import com.mygdx.game.components.menucomponents.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameState extends State {
    List<ImageButton> weaponTextures = new ArrayList<ImageButton>();
    Grid weaponGrid;
    ImageButton weaponButton;
    public GameState() {

        super();

        ImageButton bazookaButton = new ImageButton(new Texture("bazooka_temporary.png"),"bazooka");
        weaponTextures.add(bazookaButton);

        weaponGrid = new Grid(weaponTextures);
        weaponGrid.setPosition(-weaponGrid.getWidth(), GdxGame.HEIGHT - weaponGrid.getHeight());

        weaponButton = new ImageButton(new Texture("bazooka.temporary.png"), "bazooka");
        weaponButton.setPosition(5, (GdxGame.HEIGHT - weaponButton.getHeight()));

        weaponButton.addActionListener(new WeaponButtonListener());
        bazookaButton.addActionListener(new BazookaButtonListener());

        addComponent(weaponButton);
        addComponent(weaponGrid);

    }

    public void showOrHideWeaponGrid(){
        if (isVisible()){
            weaponGrid.setPosition(-weaponGrid.getWidth(), GdxGame.HEIGHT - weaponGrid.getHeight());
        }
        else {
            weaponGrid.setPosition(weaponButton.getPosition().x + weaponButton.getWidth(), GdxGame.HEIGHT - weaponGrid.getHeight());
        }
    }

    public boolean isVisible(){
        if(weaponGrid.getPosition().x<0){
            return false;
        }
        else return true;
    }

    @Override
    public void handleInput() {
        int posX = Gdx.input.getX();
        int posY = -Gdx.input.getY() + GdxGame.HEIGHT;



    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    public void dispose(){

    }
    private class WeaponButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            showOrHideWeaponGrid();
        }

    }
    private class BazookaButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            weaponButton.setTexture(new Texture("bazooka_temporary.png"));
            //change weapon logic here
        }

    }
}
