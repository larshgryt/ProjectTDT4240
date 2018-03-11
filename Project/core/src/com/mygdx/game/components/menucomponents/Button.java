package com.mygdx.game.components.menucomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
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
    }

    public void setBorderWidth(float width){
        this.borderWidth = width;
    }

    public void setText(String text){
        this.text = text;
    }
    public String getText(){
        return text;
    }

    public void setPosition(float x, float y){
        position = new Vector3(x, y, 0);
    }
    public Vector3 getPosition(){
        return position;
    }

    public void setWidth(float width){
        this.width = width;
    }
    public float getWidth(){
        return width;
    }
    public void setHeight(float height){
        this.height = height;
    }
    public float getHeight(){
        return height;
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
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(borderTexture, position.x, position.y, width + (2*borderWidth),
                height + (2*borderWidth));
        sb.draw(backgroundTexture, position.x + borderWidth, position.y + borderWidth,
                width - borderWidth, height - borderWidth);
        font.draw(sb, text, position.x + (width/2), position.y + (height/2));
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        borderTexture.dispose();
        font.dispose();
    }
}
