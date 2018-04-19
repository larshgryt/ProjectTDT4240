package com.mygdx.game.components.actors.projectiles;

public class TestProjectile extends Projectile {

    @Override
    protected Projectile getInstance() {
        TestProjectile projectile = new TestProjectile();
        return projectile;
    }

    public TestProjectile(){
        super(30, 10);
    }
}
