package com.mygdx.game.components.menucomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GdxGame;
import com.mygdx.game.components.Component;
import com.mygdx.game.listeners.EventListener;
import com.mygdx.game.sprites.ColoredField;

import java.util.ArrayList;
import java.util.EventObject;

public class Button extends Component {

    private String text;
    private ColoredField background;
    private ColoredField border;
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
        background = new ColoredField(color);
    }
    public void setBorderColor(Color color){
        border = new ColoredField(color);
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
        background = new ColoredField(Color.WHITE);
        border = new ColoredField(Color.GRAY);
        glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, text);
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
        border.render(sb, position.x, position.y,
                width + borderWidth, height + borderWidth, 0);
        background.render(sb, position.x + borderWidth, position.y + borderWidth,
                width - borderWidth, height - borderWidth, 0);
        // Text
        font.draw(sb, glyphLayout, position.x + (width - glyphLayout.width)/2 ,
                position.y + (height + glyphLayout.height)/2);

    }

    @Override
    public void dispose() {
        border.dispose();
        background.dispose();
        font.dispose();
    }
}
