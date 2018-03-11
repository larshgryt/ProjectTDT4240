package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

public class GdxGame extends ApplicationAdapter {
	public static final int WIDTH = 640;
	public static final int HEIGHT = 360;

	SpriteBatch batch;
	Texture img;
	GameStateManager gsm = GameStateManager.getInstance();


	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		gsm.push(new MenuState());
	}

	@Override
	public void render () {
	    batch.begin();
		gsm.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
