package com.mygdx.game.components.stage.stagecomponents;

import com.badlogic.gdx.graphics.Texture;

public class Tree extends StageComponent{

    public Tree(){
        super(new Texture("tree.png"));
        setCollidable(false);
    }
}
