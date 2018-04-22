package com.mygdx.game.components.gamecomponents;


import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.components.actors.projectiles.RocketProjectile;
import com.mygdx.game.sprites.Animation;

public class Bazooka extends Weapon {

    public Bazooka(){
        super(40, 15, new RocketProjectile());
        Animation sprite = new Animation(100);
        sprite.addFrame(new Texture("Rocket_Launcher.png"));
        setSprite(sprite);
        setOriginY(height/2);
        setOriginX(width / 3);
        setProjectilePoint(width, height/2);
    }
}
