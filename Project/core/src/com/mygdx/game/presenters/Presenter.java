package com.mygdx.game.presenters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;
import com.mygdx.game.listeners.EventListener;
import java.util.ArrayList;
import java.util.EventObject;

public abstract class Presenter {

    protected ArrayList<EventListener> eventListeners;
    protected ArrayList<Component> components;
    protected boolean visible;

    public Presenter(){
        eventListeners = new ArrayList<EventListener>();
        components = new ArrayList<Component>();
        visible = true;
    }

    public void notifyEvent(EventObject e){
        for(EventListener listener: eventListeners){
            listener.notifyEvent(e);
        }
    }

    public void notifyError(EventObject e, String message){
        for(EventListener listener: eventListeners){
            listener.notifyError(e, message);
        }
    }

    public void addEventListener(EventListener listener){
        this.eventListeners.add(listener);
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
