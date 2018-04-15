package com.mygdx.game.components.stage.stagecomponents;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by An on 15.04.2018.
 */

public class GrassPlatform extends StageComponent {
    public GrassPlatform(){
        super(new Texture("grass_platform.png"));
        setCollidable(true);
        setFriction(0.45f);
    }
}
