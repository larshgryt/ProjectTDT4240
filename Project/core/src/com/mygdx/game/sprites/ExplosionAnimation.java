package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;

public class ExplosionAnimation extends Animation {
    public ExplosionAnimation(float width, float height, float maxFrameTime){
        super(maxFrameTime);
        addFrame(new Texture("explosion/1.png"));
        addFrame(new Texture("explosion/2.png"));
        addFrame(new Texture("explosion/3.png"));
        addFrame(new Texture("explosion/4.png"));
        addFrame(new Texture("explosion/5.png"));
        addFrame(new Texture("explosion/6.png"));
        addFrame(new Texture("explosion/7.png"));
        addFrame(new Texture("explosion/8.png"));
        addFrame(new Texture("explosion/9.png"));
    }
}
