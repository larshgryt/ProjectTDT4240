package com.mygdx.game.components.actors.projectiles;

import com.mygdx.game.components.actors.Actor;
import com.mygdx.game.handlers.collision.CollisionBox;
import com.mygdx.game.sprites.Sprite;

public abstract class Projectile extends Actor {

    public Projectile(Sprite sprite){
        sprites.add(sprite);
        width = sprite.getWidth();
        height = sprite.getHeight();
        rotatesOnMovement = true;
        collisionBox.setCollisionMode(CollisionBox.CollisionMode.LITE);
    }

    /* Code that determines projectiles effect on another actor */
    public abstract void hit(Actor other);

    /* Code that will be ran when the projectile is destroyed (e.g explotion) */
    public abstract void destroy();

}
