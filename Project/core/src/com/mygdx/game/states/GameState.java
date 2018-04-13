package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.actors.Actor;
import com.mygdx.game.components.actors.PlayerActor;
import com.mygdx.game.components.stage.Stage;
import com.mygdx.game.components.stage.TestStage;
import com.mygdx.game.handlers.collision.Collidable;
import com.mygdx.game.handlers.collision.CollisionHandler;

import java.util.ArrayList;

public class GameState extends State {

    private Stage stage;
    private CollisionHandler collisionHandler;

    public GameState() {
        super();
        stage = new TestStage();
        collisionHandler = new CollisionHandler();
        PlayerActor player = new PlayerActor("username", 100, null, true );
        player.setPosition(200, 300);
        player.setVelocity(40, 10);
        addComponent(player);

        PlayerActor otherplayer = new PlayerActor("username", 100, null, true );
        otherplayer.setPosition(350, 400);
        otherplayer.setVelocity(-30, 10);
        addComponent(otherplayer);
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
