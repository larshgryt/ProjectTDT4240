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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Button extends Component {

    private String text;
    private Texture backgroundTexture;
    private Texture borderTexture;
    private BitmapFont font;
    private float borderWidth;
    private ArrayList<ActionListener> listeners;
    private GlyphLayout glyphLayout;

    public Button(float width, float height){
        super();
        listeners = new ArrayList<ActionListener>();
        this.width = width;
        this.height = height;
        this.text = "";
        borderWidth = 0;
        init();
    }

    public void addActionListener(ActionListener listener){
        listeners.add(listener);
    }

    public void click(){
        for(ActionListener l: listeners){
            l.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Button clicked"));
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
    }

    @Override
    public void update(float dt) {
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
        sb.draw(borderTexture, position.x, position.y, width + borderWidth,
                height + borderWidth);
        sb.draw(backgroundTexture, position.x + borderWidth, position.y + borderWidth,
                width - borderWidth, height - borderWidth);
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
