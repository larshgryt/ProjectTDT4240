package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class LobbyState extends State {
    private ShapeRenderer shapeRenderer;
    static private boolean projectionMatrixSet;
    public LobbyState(GameStateManager gsm) {
        super();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        if(!projectionMatrixSet){
            shapeRenderer.setProjectionMatrix(sb.getProjectionMatrix());
        }
        sb.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(0, 0, 50, 50);
        shapeRenderer.end();
        sb.begin();
        sb.end();
    }

    public void dispose(){

    }
}
