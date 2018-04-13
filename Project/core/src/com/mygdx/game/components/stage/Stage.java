package com.mygdx.game.components.stage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.stage.stagecomponents.StageComponent;

import java.util.ArrayList;

public abstract class Stage {

    private ArrayList<StageComponent> stageComponents;
    private Texture backgroundTexture;
    protected float width;
    protected float height;
    private float gravity;

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
        this.gravity = -981;
    }

    public void setGravity(float gravity){
        this.gravity = gravity;
    }
    public float getGravity(){
        return gravity;
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
    public ArrayList<StageComponent> getStageComponents(){
        return stageComponents;
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
