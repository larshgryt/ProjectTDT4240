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
import com.mygdx.game.presenters.GameStatePresenter;
import com.mygdx.game.presenters.PlayerListPresenter;
import com.mygdx.game.presenters.WinPresenter;
import com.mygdx.game.sprites.Line;

import java.util.ArrayList;


// In-game state. Requires a stage and a collision handler.
// Note that this is only a sample game state for testing purposes. Should be changed later.
public class GameState extends State {

    private Stage stage;
    private CollisionHandler collisionHandler;
    private GameHandler gameHandler;
    private boolean scorePresented;

    public GameState(ArrayList<String> usernames) {

        super();
        addPresenter(new GameStatePresenter());
        this.scorePresented = false;

        stage = new Forest();
        collisionHandler = new CollisionHandler(this);
        gameHandler = GameHandler.getInstance();

        boolean p = true;
        ArrayList<PlayerActor> players = new ArrayList<PlayerActor>();
        for(String user: usernames){
            PlayerActor player = new PlayerActor(user,100, new TestBazooka(), p);
            players.add(player);
            gameHandler.addPlayer(player);
            p = !p;
        }
        addPresenter(new PlayerListPresenter(players));
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
        // Set active player to start game
        gameHandler.nextTurn();
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
        for (Component c : components) {
            if (c instanceof Actor) {
                stage.applyGravityToActor((Actor) c);
            }
        }
        super.update(dt);
        collisionHandler.checkForCollisions(components, stage);
        if (gameHandler.isGameFinished()) {
            if (!this.scorePresented) {
                addPresenter(new WinPresenter(this.gameHandler.getFinishedPlayers()));
                this.scorePresented = true;
                gameHandler.setEnabled(false);
            }
        }
        if (gameHandler.getActiveProjectile() != null && !scorePresented) {
            if (containsComponent(gameHandler.getActiveProjectile())) {
                gameHandler.setEnabled(false);
            } else {
                gameHandler.setActiveProjectile(null);
                gameHandler.nextTurn();
            }
        }
        if (gameHandler.getRemovedPlayer() != null){
            removeComponent(gameHandler.getRemovedPlayer());
            gameHandler.getRemovedPlayer().dispose();
            gameHandler.refreshRemovedPlayer();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        stage.render(sb);
        super.render(sb);
        PlayerActor player = gameHandler.getActivePlayer();
        float dx = gameHandler.getDx();
        float dy = gameHandler.getDy();
        float len = (float)Math.sqrt(Math.pow(dx,2) + Math.pow(dy, 2));
        float angle = player.getAimAngle();
        if(player.isHorizontalFlip()){
            angle = 180 - angle;
        }
        if(player.isShooting()){
            (new Line()).render(sb,
                    player.getPosition().x + player.getWidth()/2,
                    player.getPosition().y + player.getHeight()/2,
                    len, 1, angle);
        }
    }

    public void dispose() {
        stage.dispose();
        super.dispose();
    }


    public GameHandler getGameHandler() {
        return gameHandler;
    }
}
