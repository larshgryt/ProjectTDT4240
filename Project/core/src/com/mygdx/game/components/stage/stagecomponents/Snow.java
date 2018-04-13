package com.mygdx.game.components.stage.stagecomponents;

public class Snow extends StageComponent {

    // A simple white block of snow.

    public Snow(int width, int height){
        super(width, height);
        setCollidable(true);
    }

}
