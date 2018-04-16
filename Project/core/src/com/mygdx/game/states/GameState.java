package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.actors.Actor;
import com.mygdx.game.components.actors.PlayerActor;
import com.mygdx.game.components.stage.Stage;
import com.mygdx.game.components.stage.TestStage;
import com.mygdx.game.handlers.collision.CollisionHandler;

import com.mygdx.game.GdxGame;
import com.mygdx.game.components.menucomponents.Button;
import com.mygdx.game.components.menucomponents.Grid;
import com.mygdx.game.components.menucomponents.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// In-game state. Requires a stage and a collision handler.
// Note that this is only a sample game state for testing purposes. Should be changed later.
public class GameState extends State {

    private Stage stage;
    private CollisionHandler collisionHandler;

    // Variables for testing purposes.
    private boolean fire;
    private PlayerActor weaponPlayer;

    List<ImageButton> weaponTextures = new ArrayList<ImageButton>();
    Grid weaponGrid;
    ImageButton weaponButton;

    public GameState() {

        super();

        stage = new TestStage();
        collisionHandler = new CollisionHandler();

        PlayerActor player = new PlayerActor("username", 100, null, true );
        player.setPosition(200, 300);
        player.setVelocity(40, 10);
        player.getWeapon().setAim(30);
        player.setVerticalFlip(false);
        addComponent(player);
        weaponPlayer = player;

        PlayerActor otherplayer = new PlayerActor("username", 100, null, true );
        otherplayer.setPosition(350, 400);
        otherplayer.setVelocity(-20, 10);
        addComponent(otherplayer);
        fire = false;

        ImageButton bazookaButton = new ImageButton(new Texture("bazooka_temporary.png"),"bazooka");
        weaponTextures.add(bazookaButton);

        weaponGrid = new Grid(weaponTextures);
        weaponGrid.setPosition(weaponGrid.getWidth(), GdxGame.HEIGHT - weaponGrid.getHeight());
        weaponGrid.positionGrid(false);

        weaponButton = new ImageButton(new Texture("bazooka.temporary.png"), "bazooka");
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

    public void showOrHideWeaponGrid(){
        if (isVisible()){
            weaponGrid.setPosition(weaponGrid.getWidth(), GdxGame.HEIGHT - weaponGrid.getHeight());
            weaponGrid.positionGrid(false);
        }
        else {
            weaponGrid.setPosition(weaponButton.getPosition().x + weaponButton.getWidth(), GdxGame.HEIGHT - weaponGrid.getHeight());
            weaponGrid.positionGrid(true);
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
        stage.update(dt);

        for(Component c: components){
            if(c instanceof Actor){
                stage.applyGravityToActor((Actor) c);
            }
        }

        super.update(dt);
        collisionHandler.checkForCollisions(components, stage);

        // Code for testing purposes.
        if(!fire){
            if(Math.abs(weaponPlayer.getVelocity().x) < 2 && Math.abs(weaponPlayer.getVelocity().y) < 2){
                addComponent(weaponPlayer.getWeapon().shoot());
                fire = true;
            }
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        stage.render(sb);
        super.render(sb);
    }


    public void dispose() {
        stage.dispose();
        super.dispose();
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
            showOrHideWeaponGrid();
            //change weapon logic here
        }

    }

    private class MenuButtonListener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        }

    }
}
