package com.mygdx.game.components.menucomponents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;

public class ImageComponent extends Component {

    Texture texture;

    public ImageComponent(Texture texture){
        super();
        this.texture = texture;
        width = texture.getWidth();
        height = texture.getHeight();
    }

    @Override
    public void update(float dt) {

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
