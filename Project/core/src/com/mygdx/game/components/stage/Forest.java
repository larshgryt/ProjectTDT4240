package com.mygdx.game.components.stage;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GdxGame;
import com.mygdx.game.components.stage.stagecomponents.GrassPlatform;
import com.mygdx.game.components.stage.stagecomponents.Ground;
import com.mygdx.game.components.stage.stagecomponents.Tree;

public class Forest extends Stage {

    public Forest(){
        super(GdxGame.WIDTH, GdxGame.HEIGHT);
        setBackgroundTexture(new Texture("forest.png"));
        Ground ground = new Ground((int)width);
        addStageComponent(ground);

        GrassPlatform platform1 = new GrassPlatform();
        GrassPlatform platform2 = new GrassPlatform();
        platform1.setPosition(144, 200);
        platform2.setPosition(450, 150);
        addStageComponent(platform1);
        addStageComponent(platform2);

        Tree tree = new Tree();
        tree.setPosition(5, ground.getHeight());
        addStageComponent(tree);
    }
}
