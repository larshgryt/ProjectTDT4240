package com.mygdx.game.components.stage.stagecomponents;


public class Border extends StageComponent {
    public Border(int width, int height){
        super(width, height);
        setCollidable(true);
        setFriction(0);
    }
}
