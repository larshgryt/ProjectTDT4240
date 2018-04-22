package com.mygdx.game.components.menucomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GdxGame;
import com.mygdx.game.components.Component;
import com.mygdx.game.listeners.EventListener;

import java.util.ArrayList;
import java.util.EventObject;

public class Button extends Component {

    private String text;
    private Texture backgroundTexture;
    private Texture borderTexture;
    private BitmapFont font;
    private float borderWidth;
    private ArrayList<EventListener> listeners;
    private GlyphLayout glyphLayout;

    public Button(float width, float height){

        /* A standard button component with click functionality */

        super();
        listeners = new ArrayList<EventListener>();
        this.width = width;
        this.height = height;
        this.text = "";
        borderWidth = 0;
        init();
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

    public void setBackgroundColor(Color color){
        backgroundTexture.dispose();
        Pixmap pixmap = new Pixmap(1,1, Pixmap.Format.RGB888);
        pixmap.setColor(color);
        pixmap.fill();
        backgroundTexture = new Texture(pixmap);
    }
    public void setBorderColor(Color color){
        borderTexture.dispose();
        Pixmap pixmap = new Pixmap(1,1, Pixmap.Format.RGB888);
        pixmap.setColor(color);
        pixmap.fill();
        borderTexture = new Texture(pixmap);
    }
    public void setFontColor(Color color){
        font.setColor(color);
        glyphLayout.setText(font, text);
    }

    public void setBorderWidth(float width){
        this.borderWidth = width;
    }

    public void setText(String text){
        this.text = text;
        glyphLayout.setText(font, text);
    }
    public String getText(){
        return text;
    }

    /* Initiates the font, textures and glyphlayouts used for this class. They may be edited
        later. */
    private void init(){
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        //Create a texture
        Pixmap pixmap = new Pixmap(1,1, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        backgroundTexture = new Texture(pixmap);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();
        borderTexture = new Texture(pixmap);
        glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, text);
        pixmap.dispose();
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

    @Override
    public void render(SpriteBatch sb) {
        // Render border
        sb.draw(borderTexture, position.x, position.y, width + borderWidth,
                height + borderWidth);
        // Background
        sb.draw(backgroundTexture, position.x + borderWidth, position.y + borderWidth,
                width - borderWidth, height - borderWidth);
        // Text
        font.draw(sb, glyphLayout, position.x + (width - glyphLayout.width)/2 ,
                position.y + (height + glyphLayout.height)/2);

    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        borderTexture.dispose();
        font.dispose();
    }
}
