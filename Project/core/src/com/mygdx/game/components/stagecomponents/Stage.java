package com.mygdx.game.components.stagecomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public abstract class Stage {

    private ArrayList<StageComponent> stageComponents;
    private Texture backgroundTexture;
    private float width;
    private float height;

    public Stage(float width, float height){
        stageComponents = new ArrayList<StageComponent>();
        //Create a white background texture
        Pixmap pixmap = new Pixmap(1,1, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        backgroundTexture = new Texture(pixmap);
        pixmap.dispose();
        this.width = width;
        this.height = height;
    }

    public void setBackgroundTexture(Texture texture){
        this.backgroundTexture = texture;
    }

    public void addStageComponent(StageComponent sc){
        stageComponents.add(sc);
    }
    public void removeStageComponent(StageComponent sc){
        stageComponents.remove(sc);
    }

    public void update(float dt){
        for(StageComponent sc: stageComponents){
            sc.update(dt);
        }
    }

    public void render(SpriteBatch sb){
        sb.draw(backgroundTexture, 0, 0, width, height);
        for(StageComponent sc: stageComponents){
            sc.render(sb);
        }
    }

    public void dispose(){
        backgroundTexture.dispose();
        for(StageComponent sc: stageComponents){
            sc.dispose();
        }
    }

}
