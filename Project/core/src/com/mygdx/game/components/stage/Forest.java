package com.mygdx.game.components.stage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.mygdx.game.components.stage.stagecomponents.Grass;
import com.mygdx.game.components.stage.stagecomponents.StageComponent;

/**
 * Created by An on 15.04.2018.
 */

public class Forest extends Stage {

    //StageComponent ground;

    public Forest(){
        super(1000, 400);
        setBackgroundTexture(new Texture("sky.jpg"));

        TiledDrawable ground = new TiledDrawable(
                new TextureRegion(
                new Texture("grass.png")));
        StageComponent groundComponent = new StageComponent(
                ground, (int) width, (int) ground.getMinHeight());
        groundComponent.setCollidable(true);
        addStageComponent(groundComponent);
    }
}
