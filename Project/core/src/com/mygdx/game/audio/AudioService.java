package com.mygdx.game.audio;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class AudioService extends ApplicationAdapter{

    private Music bgMusic;

    @Override
    public void create() {
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("background.mp3"));
        bgMusic.play();
        bgMusic.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                bgMusic.play();
            }
        });
    }

    @Override
    public void render() {

    }

    @Override
    public void dispose() {
        bgMusic.dispose();
    }

}
