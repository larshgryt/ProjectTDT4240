package com.mygdx.game.components.menucomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.GdxGame;
import com.mygdx.game.components.Component;
import com.mygdx.game.sprites.ColoredField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextInputField extends Component implements Input.TextInputListener {

    /* A component that takes input to a text field. */

    private String text;

    private String title;
    private String defaultText;
    private String hint;

    private ColoredField background;
    private ColoredField border;
    private float borderWidth;
    private BitmapFont font;
    private GlyphLayout glyphLayout;

    public TextInputField(String title, String defaultText, String hint){
        super();
        this.title = title;
        this.defaultText = defaultText;
        this.text = defaultText;
        this.hint = hint;
        this.borderWidth = 0;
        init();
    }

    public void setBackgroundColor(Color color){
        background.dispose();
        background = new ColoredField(color);
    }
    public void setBorderColor(Color color){
        border.dispose();
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
        background = new ColoredField(Color.WHITE);
        border = new ColoredField(Color.GRAY);
        glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, text);
    }

    @Override
    public void input(String text) {
        /* Called whenever the input is changed. */
        setText(text);
    }


    @Override
    public void canceled() {
    }

    @Override
    public void update(float dt) {
        if(Gdx.input.justTouched()){
            float x = Gdx.input.getX() * GdxGame.WIDTH / Gdx.app.getGraphics().getWidth();
            float y = (Gdx.app.getGraphics().getHeight() - Gdx.input.getY()) * GdxGame.HEIGHT / Gdx.app.getGraphics().getHeight();
            Vector3 touchPoint = new Vector3(x, y, 0);
            if(touchPoint.x > position.x && touchPoint.x < position.x + width && touchPoint.y > position.y && touchPoint.y < position.y + height){
                Gdx.input.getTextInput(this, title, defaultText, hint);
            }
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        border.render(sb, position.x, position.y,
                width + 2*borderWidth, height + 2*borderWidth, 0);
        background.render(sb, position.x + borderWidth, position.y + borderWidth,
                width - borderWidth, height - borderWidth, 0);
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
