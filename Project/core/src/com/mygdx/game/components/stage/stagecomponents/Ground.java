package com.mygdx.game.components.stage.stagecomponents;

import com.mygdx.game.sprites.Tiles;

public class Ground extends StageComponent {
    public Ground(int width){
        super(width, 1);
        setSprite(new Tiles("grass.png", width, 0));
        setFriction(0.45f);
        setCollidable(true);
    }
}
