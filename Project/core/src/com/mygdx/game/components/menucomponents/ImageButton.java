package com.mygdx.game.components.menucomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GdxGame;
import com.mygdx.game.components.Component;
import com.mygdx.game.listeners.EventListener;
import com.mygdx.game.sprites.Image;

import java.util.ArrayList;
import java.util.EventObject;

public class ImageButton extends Component {
    private Image image;
    private ArrayList<EventListener> listeners;

    public ImageButton(String texturePath){
        super();
        image = new Image(texturePath);
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
            }
        }
    }

    public Image getImage(){
        return image;
    }

    public void setTexture(String texturePath){
        image = new Image(texturePath);
    }


    @Override
    public void dispose() {
        image.dispose();
    }

    @Override
    public void render(SpriteBatch sb) {
        image.render(sb, position.x, position.y, image.getWidth(), image.getHeight(), 0);
    }
}
