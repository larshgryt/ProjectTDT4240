package com.mygdx.game.components.gamecomponents;


import com.mygdx.game.components.actors.projectiles.TestProjectile;

public class TestBazooka extends Weapon {

    public TestBazooka(){
        super(50, 15, new TestProjectile());
    }



    @Override
    public void fire(float angle) {

    }
}
