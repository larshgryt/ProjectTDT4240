package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;

import java.util.ArrayList;

public abstract class State {

    private ArrayList<Component> components;

    public State(){
        components = new ArrayList<Component>();
    }

    protected ArrayList<Component> getAllComponents(){
        return components;
    }
    protected void addComponent(Component component){
        components.add(component);
    }
    protected boolean removeComponent(Component component){
        for(Component c: components){
            if(component.equals(c)){
                components.remove(c);
                return true;
            }
        }
        return false;
    }
    protected Component removeComponent(int index){
        if(index < 0 || index >= components.size()){
            throw new IndexOutOfBoundsException();
        }
        else{
            return components.remove(index);
        }
    }

    protected Component getComponent(int index){
        if(index < 0 || index >= components.size()){
            throw new IndexOutOfBoundsException();
        }
        else{
            return components.get(index);
        }
    }
    protected int indexOfComponent(Component component){
        return components.indexOf(component);
    }

    abstract void handleInput();

    public void update(float dt){
        for(Component component: components){
            component.update(dt);
        }
    }
    public void render(SpriteBatch sb){
        for(Component component: components){
            component.render(sb);
        }
    }
    public void dispose(){
        for(Component component: components){
            component.dispose();
        }
    }

}