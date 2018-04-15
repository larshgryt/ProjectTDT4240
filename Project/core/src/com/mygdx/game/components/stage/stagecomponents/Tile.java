package com.mygdx.game.components.stage.stagecomponents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;

/**
 * Created by An on 15.04.2018.
 */

public class Tile extends StageComponent{

    //A tile which repeats inside of area bounded by position, width and height

    public Tile(Texture texture, int width, int height){
        super(new TiledDrawable(
                new TextureRegion(
                        texture)), width, height);
        setCollidable(true);
    }
}
