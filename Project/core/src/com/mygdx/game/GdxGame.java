package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.handlers.input.GameInputProcessor;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

public class GdxGame extends ApplicationAdapter {
	public static final int WIDTH = 640;
	public static final int HEIGHT = 360;

	SpriteBatch batch;
	GameStateManager gsm = GameStateManager.getInstance();

	@Override
	public void create () {
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(new GameInputProcessor());
		gsm.push(new MenuState());
	}

	@Override
	public void render () {
	    batch.begin();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		gsm.dispose();
	}
}
