package com.mygdx.game.components.stage;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.components.stage.stagecomponents.Snow;

public class TestStage extends Stage {

    public TestStage(){
        super(1000, 400);
        setBackgroundTexture(new Texture("sky.jpg"));
        addStageComponent(new Snow((int) width, 80));

        Snow platform = new Snow(100, 30);
        platform.setPosition(200, 200);
        addStageComponent(platform);
    }

}
