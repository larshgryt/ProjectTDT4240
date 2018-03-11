package com.mygdx.game.components.menucomponents;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;

public class TextLabel extends Component {

    private String text;
    private GlyphLayout glyphLayout;
    private BitmapFont font;
    private Color color;

    public TextLabel(){
        text = "";
        glyphLayout = new GlyphLayout();
        font = new BitmapFont();
        color = Color.BLACK;
        position.x = 0;
        position.y = 0;
        width = 0;
        height = 0;

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void dispose() {

    }
}
