package com.mygdx.game.components.stage.stagecomponents;

import com.badlogic.gdx.graphics.Texture;

public class GrassPlatform extends StageComponent {
    public GrassPlatform(){
        super(new Texture("grass_platform.png"));
        setCollidable(true);
        setFriction(0.45f);
    }
}
