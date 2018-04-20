package com.mygdx.game.states;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.actors.Actor;
import com.mygdx.game.components.actors.PlayerActor;
import com.mygdx.game.components.gamecomponents.TestBazooka;
import com.mygdx.game.components.stage.Forest;
import com.mygdx.game.components.stage.Stage;
import com.mygdx.game.handlers.GameHandler;
import com.mygdx.game.handlers.collision.CollisionHandler;
import com.mygdx.game.listeners.EventListener;
import com.mygdx.game.presenters.GameStatePresenter;
import com.mygdx.game.presenters.Presenter;

import java.util.EventObject;

import java.util.ArrayList;


// In-game state. Requires a stage and a collision handler.
// Note that this is only a sample game state for testing purposes. Should be changed later.
public class GameState extends State {

    private Stage stage;
    private CollisionHandler collisionHandler;
    private GameHandler gameHandler;

    public GameState(ArrayList<String> usernames) {

        super();
        addPresenter(new GameStatePresenter());

        stage = new Forest();
        collisionHandler = new CollisionHandler(this);
        gameHandler = new GameHandler();

        boolean p = true;
        ArrayList<PlayerActor> players = new ArrayList<PlayerActor>();
        for(String user: usernames){
            PlayerActor player = new PlayerActor(user,100, new TestBazooka(), p);
            players.add(player);
            gameHandler.addPlayer(player);
            p = !p;
        }
        int penguins = 0;
        int snowmen = 1;
        for(PlayerActor player: players){
            if(player.isPenguin()){
                player.setPosition(10 + penguins * (PlayerActor.DEFAULT_WIDTH *2), 50);
                penguins++;
            }
            else{
                player.setPosition(stage.getWidth() - 10 - snowmen * (PlayerActor.DEFAULT_WIDTH *2), 50);
                player.setHorizontalFlip(true);
                snowmen++;
            }
            addComponent(player);
        }
    }

    @Override
    public boolean containsComponent(Component component){
        return(super.containsComponent(component) || stage.getStageComponents().contains(component));
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


    public void dispose() {
        stage.dispose();
        super.dispose();
    }


    public GameHandler getGameHandler() {
        return gameHandler;
    }
}
