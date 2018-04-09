package com.mygdx.game.components.menucomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

public class TextInputField extends Component implements Input.TextInputListener {

    /* A component that takes input to a text field. */

    private String text;

    private String title;
    private String defaultText;
    private String hint;

    private Texture backgroundTexture;
    private Texture borderTexture;
    private float borderWidth;
    private BitmapFont font;
    private GlyphLayout glyphLayout;

    private ActionListener listener;

    public TextInputField(String title, String defaultText, String hint){
        super();
        this.title = title;
        this.defaultText = defaultText;
        this.text = defaultText;
        this.hint = hint;
        this.borderWidth = 0;
        init();
    }

    public void setActionListener(ActionListener listener){
        this.listener = listener;
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

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

    public void setDefaultText(String text){
        this.defaultText = text;
    }
    public String getDefaultText(){
        return defaultText;
    }

    public void setHint(String hint){
        this.hint = hint;
    }
    public String getHint(){
        return hint;
    }

    /* Initiates fonts, glyphlayout and textures used, so that they may be edited later. */
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
    public void input(String text) {
        /* Called whenever the input is changed. */
        setText(text);
        if(listener != null){
            listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, text));
        }
    }

    @Override
    public void canceled() {
    }

    @Override
    public void update(float dt) {
        if(Gdx.input.justTouched()){
            float x = Gdx.input.getX();
            float y = GdxGame.HEIGHT - Gdx.input.getY();
            if(x > position.x && x < position.x + width && y > position.y && y < position.y + height){
                Gdx.input.getTextInput(this, title, defaultText, hint);
            }
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(borderTexture, position.x, position.y,
                width + 2*borderWidth, height + 2*borderWidth);
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
