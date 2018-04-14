package com.mygdx.game.components.gamecomponents;


import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.components.actors.projectiles.TestProjectile;
import com.mygdx.game.sprites.Animation;

public class TestBazooka extends Weapon {

    public TestBazooka(){
        super(40, 15, new TestProjectile());
        Animation sprite = new Animation(100);
        sprite.addFrame(new Texture("Rocket_Launcher.png"));
        setSprite(sprite);
        setOriginY(height/2);
        setOriginX(width / 4);
    }



    @Override
    public void fire(float angle) {

    }
}
