package com.mygdx.game.components.stage.stagecomponents;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by An on 15.04.2018.
 */

public class Tree extends StageComponent{

    public Tree(){
        super(new Texture("tree.png"));
        setCollidable(false);
    }
}
