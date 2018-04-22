package com.mygdx.game.components.gamecomponents;

import com.mygdx.game.components.actors.projectiles.RocketProjectile;
import com.mygdx.game.sprites.Animation;

public class Bazooka extends Weapon {

    public Bazooka(){
        super(40, 15, new RocketProjectile());
        Animation animation = new Animation(100);
        animation.addFrame("Rocket_Launcher.png");
        setSprite(animation);
        setOriginY(height/2);
        setOriginX(width / 3);
        setProjectilePoint(width, height/2);
    }
}
