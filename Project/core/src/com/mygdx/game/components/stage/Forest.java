package com.mygdx.game.components.stage;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.components.stage.stagecomponents.GrassPlatform;
import com.mygdx.game.components.stage.stagecomponents.StageComponent;
import com.mygdx.game.components.stage.stagecomponents.Tile;

/**
 * Created by An on 15.04.2018.
 */

public class Forest extends Stage {

    public Forest(){
        super(1000, 400);
        setBackgroundTexture(new Texture("sky.jpg"));

        Texture grassTile = new Texture("grass.png");
        StageComponent grassTileComponent = new Tile(grassTile, (int) width, grassTile.getHeight());
        grassTileComponent.setFriction(0.45f);
        addStageComponent(grassTileComponent);

        GrassPlatform platform1 = new GrassPlatform();
        GrassPlatform platform2 = new GrassPlatform();
        platform1.setPosition(144, 200);
        platform2.setPosition(450, 100);
        addStageComponent(platform1);
        addStageComponent(platform2);
    }
}
