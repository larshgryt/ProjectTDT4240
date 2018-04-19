package com.mygdx.game.states;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.actors.Actor;
import com.mygdx.game.components.actors.PlayerActor;
import com.mygdx.game.components.stage.Forest;
import com.mygdx.game.components.stage.Stage;
import com.mygdx.game.handlers.collision.CollisionHandler;
import com.mygdx.game.presenters.GameStatePresenter;


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
        addPresenter(new GameStatePresenter());
        stage = new Forest();
        collisionHandler = new CollisionHandler(this);

        PlayerActor player = new PlayerActor("username", 100, null, true );
        player.setPosition(100, 305);
        player.setVelocity(10, 100);
        player.setAcceleration(0, stage.getGravity());
        player.setAngle(40);
        player.getWeapon().setAim(30);
        player.setVerticalFlip(false);
        addComponent(player);
        weaponPlayer = player;
        PlayerActor otherplayer = new PlayerActor("username", 100, null, true );
        otherplayer.setPosition(350, 300);
        otherplayer.setVelocity(-100, 0);
        otherplayer.setAcceleration(0, stage.getGravity());
        addComponent(otherplayer);
        otherplayer.setAngle(0);
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
        //weaponPlayer.setAngle(weaponPlayer.getAngle() + 1);
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
}
