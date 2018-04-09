package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;

public class PenguinAnimation extends Animation {

    public PenguinAnimation(float maxFrameTime){
        super(maxFrameTime);
        addFrame(new Texture("temp-penguin.png"));
    }

}
