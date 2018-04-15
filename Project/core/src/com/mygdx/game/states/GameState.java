package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.actors.Actor;
import com.mygdx.game.components.actors.PlayerActor;
import com.mygdx.game.components.stage.Forest;
import com.mygdx.game.components.stage.Stage;
import com.mygdx.game.components.stage.TestStage;
import com.mygdx.game.handlers.collision.CollisionHandler;


// In-game state. Requires a stage and a collision handler.
// Note that this is only a sample game state for testing purposes. Should be changed later.
public class GameState extends State {

    private Stage stage;
    private CollisionHandler collisionHandler;

    // Variables for testing purposes.
    private boolean fire;
    private PlayerActor weaponPlayer;

    public GameState() {
        super();
        stage = new Forest();
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

    }

    @Override
    public void handleInput() {

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

    public void dispose(){
        stage.dispose();
        super.dispose();
    }
}
