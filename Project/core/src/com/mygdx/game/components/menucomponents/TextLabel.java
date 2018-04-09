package com.mygdx.game.components.menucomponents;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;

public class TextLabel extends Component {

    /* A component that shows a text label on the sreen. */

    private String text;
    private GlyphLayout glyphLayout;
    private BitmapFont font;

    public TextLabel(String string){
        super();
        glyphLayout = new GlyphLayout();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        setText(string);
    }

    public void setColor(Color color){
        font.setColor(color);
        glyphLayout.setText(font, text);
    }

    public void setText(String text){
        this.text = text;
        glyphLayout.setText(font, text);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        font.draw(sb, glyphLayout, position.x + (width - glyphLayout.width)/2 ,
                position.y + (height + glyphLayout.height)/2);
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
