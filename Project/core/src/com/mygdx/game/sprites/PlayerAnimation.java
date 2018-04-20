package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;

public class PlayerAnimation extends Animation {

    public PlayerAnimation(float maxFrameTime, boolean penguin){
        super(maxFrameTime);
        if(penguin){
            addFrame(new Texture("temp-penguin.png"));
        }
        else{
            addFrame(new Texture("snowman.png"));
        }
    }

}
