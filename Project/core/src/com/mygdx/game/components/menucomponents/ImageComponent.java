package com.mygdx.game.components.menucomponents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;
import com.mygdx.game.sprites.Image;

public class ImageComponent extends Component {

    /* A component that shows an image on the screen. */

    Image image;

    public ImageComponent(String texturePath){
        super();
        image = new Image(texturePath);
        width = image.getWidth();
        height = image.getHeight();
    }

    public Image getImage() {
        return image;
    }

    public void setImage(String texturePath) {
        image = new Image(texturePath);
    }

    @Override
    public void render(SpriteBatch sb) {
        image.render(sb, position.x, position.y, width, height, 0);
    }

    @Override
    public void dispose() {
        image.dispose();
    }
}
