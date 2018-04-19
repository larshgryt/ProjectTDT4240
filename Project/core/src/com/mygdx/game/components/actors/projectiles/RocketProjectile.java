package com.mygdx.game.components.actors.projectiles;

import com.badlogic.gdx.graphics.Texture;

public class RocketProjectile extends Projectile {


    @Override
    protected Projectile getInstance() {
        RocketProjectile projectile = new RocketProjectile();
        return projectile;
    }

    public RocketProjectile(){
        super(new Texture("rocket.png"));
        setWidth(30);
        setHeight(14);
    }
}
