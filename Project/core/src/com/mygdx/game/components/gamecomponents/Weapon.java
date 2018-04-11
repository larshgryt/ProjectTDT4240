package com.mygdx.game.components.gamecomponents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.actors.projectiles.Projectile;

public abstract class Weapon extends Component {

    private Texture texture;
    private Projectile projectile;

    public Weapon(Texture texture, Projectile projectile){
        this.texture = texture;
        this.projectile = projectile;
        width = texture.getWidth();
        height = texture.getHeight();
    }

    public abstract void fire(float angle);

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(texture, position.x, position.y, 0, 0, width, height, 1, 1,
                (float) Math.toRadians(angle),0, 0, texture.getWidth(),
                texture.getHeight(), horizontalFlip, verticalFlip);
    }

    @Override
    public void dispose() {
        texture.dispose();
        projectile.dispose();
    }
}
