package com.mygdx.game.components.actors.projectiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.components.actors.Actor;
import com.mygdx.game.handlers.collision.CollisionBox;
import com.mygdx.game.sprites.Animation;
import com.mygdx.game.sprites.Sprite;

public abstract class Projectile extends Actor {

    public Projectile(Sprite sprite){
        sprites.add(sprite);
        width = sprite.getWidth();
        height = sprite.getHeight();
        rotatesOnMovement = true;
        collisionBox.setCollisionMode(CollisionBox.CollisionMode.LITE);
    }
    public Projectile(int width, int height){
        Animation sprite = new Animation(100);
        //Create a white background texture
        Pixmap pixmap = new Pixmap(width,height, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        sprite.addFrame(new Texture(pixmap));
        pixmap.dispose();
        sprites.add(sprite);
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
        rotatesOnMovement = true;
        collisionBox.setCollisionMode(CollisionBox.CollisionMode.LITE);
    }

    /* Code that determines projectiles effect on another actor */
    public abstract void hit(Actor other);

    /* Code that will be ran when the projectile is destroyed (e.g explotion) */
    public abstract void destroy();

}
