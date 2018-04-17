package com.mygdx.game.presenters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;

import java.util.ArrayList;

public abstract class Presenter {

    protected ArrayList<Component> components;
    protected boolean visible;

    public Presenter(){
        components = new ArrayList<Component>();
        visible = true;
    }

    protected void addComponent(Component component){
        this.components.add(component);
    }

    protected void removeComponent(Component component){
        this.components.remove(component);
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }
    public boolean isVisible(){
        return visible;
    }

    public void update(float dt){
        for(Component c: components){
            c.update(dt);
        }
    }
    public void render(SpriteBatch sb){
        if(isVisible()){
            for(Component c: components){
                c.render(sb);
            }
        }
    }
    public void dispose(){
        for(Component c: components){
            c.dispose();
        }
    }

}
