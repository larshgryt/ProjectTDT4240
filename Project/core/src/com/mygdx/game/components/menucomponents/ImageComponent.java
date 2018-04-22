package com.mygdx.game.components.menucomponents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;

public class ImageComponent extends Component {

    /* A component that shows an image on the screen. */

    Texture texture;

    public ImageComponent(Texture texture){
        super();
        this.texture = texture;
        width = texture.getWidth();
        height = texture.getHeight();
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(texture, position.x, position.y, width, height);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
