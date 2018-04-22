package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GdxGame;
import com.mygdx.game.audio.AudioService;
import com.mygdx.game.components.Component;
import com.mygdx.game.presenters.Presenter;
import java.util.ArrayList;

/* Standard abstract class for views. May be extended for spesific views.*/

public abstract class State {

    protected ArrayList<Component> components;  // Components to be rendered.
    protected ArrayList<Presenter> presenters;  // Presenters (GUI) to be rendered.
    protected AudioService audioService = new AudioService();
    protected OrthographicCamera cam;

    public State(){
        components = new ArrayList<Component>();
        presenters = new ArrayList<Presenter>();
        audioService.create();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, GdxGame.WIDTH, 400);
    }

    protected void setMusic(String path){
        audioService.set(path);
    }

    public void addComponent(Component component){
        components.add(component);
    }
    public boolean removeComponent(Component component){
        for(Component c: components){
            if(c == component){
                components.remove(c);
                return true;
            }
        }
        return false;
    }
    public boolean containsComponent(Component component){
        return(components.contains(component));
    }
    protected void addPresenter(Presenter presenter){
        presenters.add(presenter);
    }
    protected void removePresenter(Presenter presenter){
        presenters.remove(presenter);
    }
    abstract void handleInput();

    public void update(float dt){
        for(Component component: components){
            component.update(dt);
        }
        for(Presenter presenter: presenters){
            presenter.update(dt);
        }
        handleInput();
    }
    public void render(SpriteBatch sb){
        sb.setProjectionMatrix(cam.combined);
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

    public ArrayList<Presenter> getPresenters() {
        return presenters;
    }

}