package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.audio.AudioService;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

public class GdxGame extends ApplicationAdapter {
	public static final int WIDTH = 640;
	public static final int HEIGHT = 360;

	SpriteBatch batch;
	Texture img;
	GameStateManager gsm = GameStateManager.getInstance();
	AudioService audioService = new AudioService();




	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		gsm.push(new MenuState());
		audioService.create();
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
		img.dispose();
	}
}
