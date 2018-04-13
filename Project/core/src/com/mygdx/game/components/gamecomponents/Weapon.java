package com.mygdx.game.components.gamecomponents;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.actors.projectiles.Projectile;
import com.mygdx.game.sprites.Animation;
import com.mygdx.game.sprites.Sprite;

public abstract class Weapon extends Component {

    private Projectile projectile;
    private Sprite sprite;

    public Weapon(Sprite sprite, Projectile projectile){
        this.sprite = sprite;
        this.projectile = projectile;
        width = sprite.getWidth();
        height = sprite.getHeight();
    }
    public Weapon(int width, int height, Projectile projectile){
        Animation sprite = new Animation(100);
        //Create a white texture
        Pixmap pixmap = new Pixmap(width,height, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        sprite.addFrame(new Texture(pixmap));
        pixmap.dispose();
        this.sprite = sprite;
        this.projectile = projectile;
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
    }

    public void setSprite(Sprite sprite){
        this.sprite = sprite;
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
    }

    public void setProjectile(Projectile projectile){
        this.projectile = projectile;
    }

    public abstract void fire(float angle);

    @Override
    public void render(SpriteBatch sb) {
        if(sprite != null){
            sprite.render(sb, position.x, position.y, width, height, angle);
        }
    }

    @Override
    public void dispose() {
        sprite.dispose();
        projectile.dispose();
    }
}
