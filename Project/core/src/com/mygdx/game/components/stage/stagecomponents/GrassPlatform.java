package com.mygdx.game.components.stage.stagecomponents;

public class GrassPlatform extends StageComponent {
    public GrassPlatform(){
        super("grass_platform.png");
        setCollidable(true);
        setFriction(0.45f);
    }
}
