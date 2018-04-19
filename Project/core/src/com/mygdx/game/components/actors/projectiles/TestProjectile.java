package com.mygdx.game.components.actors.projectiles;

import com.mygdx.game.components.actors.Actor;

public class TestProjectile extends Projectile {

    @Override
    protected Projectile getInstance() {
        TestProjectile projectile = new TestProjectile();
        return projectile;
    }

    public TestProjectile(){
        super(30, 10);
    }

    @Override
    public void hit(Actor other) {

    }

    @Override
    public void destroy() {

    }
}
