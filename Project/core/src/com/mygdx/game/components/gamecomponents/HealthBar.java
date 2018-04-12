package com.mygdx.game.components.gamecomponents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;

/**
 * Created by agava on 12.03.2018.
 */

public class HealthBar extends Component {
    private Texture borderTexture;
    private Texture fillTexture;

    public HealthBar(float width, float height){
        super();
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw();
    }

    @Override
    public void dispose() {

    }
}
