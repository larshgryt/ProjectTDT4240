package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GdxGame;
import com.mygdx.game.components.menucomponents.Button;
import com.mygdx.game.components.menucomponents.Grid;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameState extends State {
    List<Texture> weaponTextures = new ArrayList<Texture>();
    Grid weaponGrid;
    Button weaponButton;
    public GameState() {

        super();

        Texture bazooka = new Texture("bazooka_temporary.png");
        weaponTextures.add(bazooka);

        weaponGrid = new Grid(weaponTextures);
        weaponGrid.setPosition(-weaponGrid.getWidth(), GdxGame.HEIGHT - weaponGrid.getHeight());

        weaponButton = new Button(30, 30);
        weaponButton.setText("Bazooka");
        weaponButton.setPosition(5, (GdxGame.HEIGHT - weaponButton.getHeight()));
        weaponButton.setBorderWidth(2);
        weaponButton.setFontColor(Color.WHITE);
        weaponButton.setBackgroundColor(Color.BLACK);
        weaponButton.setBorderColor(Color.BLACK);
        weaponButton.addActionListener(new WeaponButtonListener());

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
}
