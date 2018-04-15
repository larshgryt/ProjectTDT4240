package com.mygdx.game.components.stage;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.components.stage.stagecomponents.Tile;

/**
 * Created by An on 15.04.2018.
 */

public class Forest extends Stage {

    public Forest(){
        super(1000, 400);
        setBackgroundTexture(new Texture("sky.jpg"));

        Texture grassTile = new Texture("grass.png");
        addStageComponent(new Tile(grassTile, (int) width, grassTile.getHeight()));
    }
}
