package com.mygdx.game.components.actors.projectiles;

import com.mygdx.game.components.gamecomponents.VisualEffect;
import com.mygdx.game.sprites.ExplosionAnimation;

public class RocketProjectile extends Projectile {


    @Override
    protected Projectile getInstance() {
        RocketProjectile projectile = new RocketProjectile();
        hitEffect = new VisualEffect(null, new ExplosionAnimation(15, 15, 50), 50);
        return projectile;
    }

    public RocketProjectile(){
        super("rocket.png");
        setWidth(30);
        setHeight(14);
    }
}
