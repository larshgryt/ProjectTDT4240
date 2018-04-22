package com.mygdx.game.components.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.components.gamecomponents.TestBazooka;
import com.mygdx.game.components.gamecomponents.Weapon;
import com.mygdx.game.handlers.collision.CollisionBox;
import com.mygdx.game.sprites.Animation;
import com.mygdx.game.sprites.PlayerAnimation;

public class PlayerActor extends Actor {

    public static final int DEFAULT_WIDTH = 28;
    public static final int DEFAULT_HEIGHT = 40;
    public int playerNumber;
    private String username;
    private Weapon weapon;          // The actors current held weapon.
    private Vector3 holdingPoint;   // Weapon holding point relative to the player's coordinates.
    private boolean penguin;        // Whether the player actor is a penguin.
    private float health;           // Health. Should not actually be here.

    public PlayerActor(String username, float maxHealth, Weapon initialWeapon, boolean penguin){
        super();
        setUsername(username);
        this.weapon = initialWeapon;
        this.penguin = penguin;
        collisionBox.setCollisionMode(CollisionBox.CollisionMode.PASSIVE);
        setWidth(DEFAULT_WIDTH);
        setHeight(DEFAULT_HEIGHT);
        health = 100;
        addSprite(new PlayerAnimation(100, penguin));
        setRotatesOnMovement(false);
        bounces = true;
        holdingPoint = new Vector3(width/2, height/2, 0);
        setWeapon(new TestBazooka());
        setElasticity(0.4f);
        setBounceThreshold(10);
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
        super.update(dt);

        // Rotate the holding point around the center so that it matches the players angle.

        // Original holding point
        float hx = position.x + holdingPoint.x;
        float hy = position.y + holdingPoint.y;

        // Center around which the holding point will be rotated
        float cx = position.x + width/2;
        float cy = position.y + height/2;

        // Angle in radians.
        double theta = Math.toRadians(getAngle());

        // If the actor is flipped, then flip the holding point across the center.
        float dx = cx - hx;
        float dy = cy - hy;
        if(horizontalFlip){
            hx += 2 * dx;
        }
        if(verticalFlip){
            hy += 2 * dy;
        }

        // Shift the point so that it may rotate around the origin.
        hx -= cx;
        hy -= cy;

        // Rotate point around the origin
        float x = hx * (float)Math.cos(theta) - hy * (float)Math.sin(theta);
        float y = hx * (float)Math.sin(theta) + hy * (float)Math.cos(theta);

        // Shift back to original center.
        x += cx;
        y += cy;
        weapon.setPosition(x, y);
        weapon.setHorizontalFlip(horizontalFlip);
        weapon.setVerticalFlip(verticalFlip);
    }
    public void setHealth(float health){
        this.health = health;
    }
    public float getHealth(){
        return health;
    }
    @Override
    public boolean isMoving() {
        return moving;
    }
    @Override
    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
