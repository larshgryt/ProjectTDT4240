package com.mygdx.game.components.menucomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GdxGame;
import com.mygdx.game.components.Component;
import com.mygdx.game.listeners.EventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;

public class ImageButton extends Component {
    private Texture image;
    private String name;
    private ArrayList<EventListener> listeners;

    public ImageButton(Texture texture, String name){
        super();
        image = texture;
        this.name = name;
        listeners = new ArrayList<EventListener>();
        width = image.getWidth();
        height = image.getHeight();
    }

    public void addEventListener(EventListener listener){
        listeners.add(listener);
    }

    /* Notifies all listeners that the button has been clicked */
    public void click(){
        for(EventListener l: listeners){
            l.notifyEvent(new EventObject(this));
        }
    }

    @Override
    public void update(float dt) {
        /* If input was a touch in the button area of the screen, call click method. */
        if(Gdx.input.justTouched()){
            float x = Gdx.input.getX();
            float y = GdxGame.HEIGHT - Gdx.input.getY();
            if(x > position.x && x < position.x + width && y > position.y && y < position.y + height){
                click();
                System.out.println("Button clicked.");
            }
        }
    }

    public Texture getTexture(){
        return image;
    }

    public void setTexture(Texture texture){
        image = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public void dispose() {
        image.dispose();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(image, position.x, position.y, width, height );
    }
}
