package com.mygdx.game.components.stage.stagecomponents;


import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.sprites.Tiles;

public class Ground extends StageComponent {
    public Ground(int width){
        super(width, 1);
        Texture texture = new Texture(("grass.png"));
        setSprite(new Tiles(texture, width, texture.getHeight()));
        setFriction(0.45f);
        setCollidable(true);
    }
}
