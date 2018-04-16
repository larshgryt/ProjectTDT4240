package com.mygdx.game.components.stage;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.components.stage.stagecomponents.Snow;

public class TestStage extends Stage {

    // A stage made for testing and demonstrating collision and gravity.

    public TestStage(){
        super(1000, 400);
        setBackgroundTexture(new Texture("sky.jpg"));
        addStageComponent(new Snow((int) width, 80));

        Snow platform = new Snow(300, 30);
        platform.setPosition(10, 150);
        platform.setAngle(0);
        addStageComponent(platform);
    }

}
