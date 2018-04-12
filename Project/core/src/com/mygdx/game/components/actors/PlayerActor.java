package com.mygdx.game.components.actors;

import com.mygdx.game.components.gamecomponents.Weapon;
import com.mygdx.game.handlers.collision.CollisionBox;
import com.mygdx.game.sprites.PenguinAnimation;

public class PlayerActor extends Actor {

    private String username;
    private float maxHealth;
    private float health;
    private Weapon weapon;
    private boolean penguin;

    public PlayerActor(String username, float maxHealth, Weapon initialWeapon, boolean penguin){
        super();
        this.username = username;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.weapon = initialWeapon;
        this.penguin = penguin;
        collisionBox.setCollisionMode(CollisionBox.CollisionMode.ACTIVE);
        if(penguin){
            setWidth(64);
            setHeight(80);
            addSprite(new PenguinAnimation(100));
        }
        setRotatesOnMovement(false);
    }

    @Override
    public void setVelocity(float x, float y){
        super.setVelocity(x, y);
        if(x < 0){
            setHorizontalFlip(true);
        }
        else{
            setHorizontalFlip(false);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public boolean isPenguin() {
        return penguin;
    }

    public void setPenguin(boolean penguin) {
        this.penguin = penguin;
    }
}
