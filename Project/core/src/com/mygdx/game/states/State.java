package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.audio.AudioService;
import com.mygdx.game.components.Component;
import com.mygdx.game.presenters.Presenter;

import java.awt.Event;
import java.util.ArrayList;
import java.util.EventListener;

import javax.swing.event.ChangeEvent;

/* Standard abstract class for views. May be extended for spesific views.*/

public abstract class State {

    protected ArrayList<Component> components;  // Components to be rendered.
    protected ArrayList<Presenter> presenters;  // Presenters (GUI) to be rendered.
    protected AudioService audioService = new AudioService();

    public State(){
        components = new ArrayList<Component>();
        presenters = new ArrayList<Presenter>();
        audioService.create();
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
    protected void addPresenter(Presenter presenter){
        presenters.add(presenter);
    }
    protected void removePresenter(Presenter presenter){
        presenters.remove(presenter);
    }
    abstract void handleInput();

    public void update(float dt){
        handleInput();
        for(Component component: components){
            component.update(dt);
        }
        for(Presenter presenter: presenters){
            presenter.update(dt);
        }
    }
    public void render(SpriteBatch sb){
        for(Component component: components){
            component.render(sb);
        }
        for(Presenter presenter: presenters){
            presenter.render(sb);
        }
    }
    public void dispose(){
        for(Component component: components){
            component.dispose();
        }
        for(Presenter presenter: presenters){
            presenter.dispose();
        }
    }

}