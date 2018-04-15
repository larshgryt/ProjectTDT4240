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
        //addStageComponent(new Grass((int)width, 64));

        //Grass platform = new Grass(250, 64);
        //platform.setPosition(144, 200);
        //addStageComponent(platform);

        TiledDrawable ground = new TiledDrawable(
                new TextureRegion(
                new Texture("grass.png")));
        StageComponent groundComponent = new StageComponent(
                ground, (int) width, (int) ground.getMinHeight());
        groundComponent.setCollidable(true);
        addStageComponent(groundComponent);
    }

/*    @Override
    public void render(SpriteBatch sb){
        super.render(sb);
        ground.draw(sb, 0, 0, width, ground.getMinHeight());
    }*/
}
