package com.mygdx.game.components.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.components.gamecomponents.TestBazooka;
import com.mygdx.game.components.gamecomponents.Weapon;
import com.mygdx.game.handlers.collision.CollisionBox;
import com.mygdx.game.sprites.PenguinAnimation;

public class PlayerActor extends Actor {

    private String username;
    private float maxHealth;
    private float health;
    private Weapon weapon;
    private Vector3 holdingPoint;   // Holding point relative to the player's coordinates.
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
            setWidth(32);
            setHeight(40);
            addSprite(new PenguinAnimation(100));
        }
        setRotatesOnMovement(false);
        bounces = true;
        holdingPoint = new Vector3(position.x + width/2, position.y + height/2, 0);
        setWeapon(new TestBazooka());
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

    @Override
    public void setAngle(float angle){
        double theta = Math.toRadians(angle) - getAngle();
        double newX = holdingPoint.x * Math.cos(theta) - holdingPoint.y * Math.sin(theta);
        double newY = holdingPoint.x * Math.sin(theta) + holdingPoint.y * Math.cos(theta);
        holdingPoint.set((float)newX, (float)newY, 0);
        super.setAngle(angle);
        weapon.setAngle(angle);
    }

    @Override
    public void render(SpriteBatch sb){
        super.render(sb);
        if(weapon != null){
            weapon.render(sb);
        }
    }

    @Override
    public void update(float dt){

        weapon.setPosition(position.x + holdingPoint.x, position.y + holdingPoint.y);
        weapon.setHorizontalFlip(horizontalFlip);
        weapon.setVerticalFlip(verticalFlip);
        if(horizontalFlip){
            weapon.setPosition(weapon.getPosition().x - weapon.getWidth(), weapon.getPosition().y);
        }
        if(verticalFlip){
            weapon.setPosition(weapon.getPosition().x, weapon.getPosition().y - weapon.getHeight());
        }
        super.update(dt);
    }
}
