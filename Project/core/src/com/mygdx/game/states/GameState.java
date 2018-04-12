package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.stage.Stage;
import com.mygdx.game.components.stage.TestStage;

public class GameState extends State {

    private Stage stage;

    public GameState() {
        super();
        stage = new TestStage();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        stage.update(dt);
        super.update(dt);
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
