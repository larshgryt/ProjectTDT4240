package com.mygdx.game.audio;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class AudioService extends ApplicationAdapter{

    private Music bgMusic;

    public void set(String path){
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal(path));
        bgMusic.play();
        bgMusic.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                bgMusic.play();
            }
        });
    }

    @Override
    public void create() {

    }

    @Override
    public void render() {

    }

    @Override
    public void dispose() {
        bgMusic.dispose();
    }

}
