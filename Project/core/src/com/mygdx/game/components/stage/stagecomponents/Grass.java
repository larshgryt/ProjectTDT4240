package com.mygdx.game.components.stage.stagecomponents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;

/**
 * Created by An on 15.04.2018.
 */

public class Grass extends StageComponent {
    public Grass(int width, int height){
        super(width, height);

        Texture grass = new Texture("grass.png");
        //grass.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        //setTexture(grass);

    }
}
