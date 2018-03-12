package com.mygdx.game.audio;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class AudioService extends ApplicationAdapter{

    Sound bgMusic;

    @Override
    public void create() {
        bgMusic = Gdx.audio.newSound(Gdx.files.internal("background.mp3"));
        long id = bgMusic.loop();
    }

    @Override
    public void render() {

    }

    @Override
    public void dispose() {
        bgMusic.dispose();
    }

}
