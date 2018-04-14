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
            /*
            Pixmap pixmap = new Pixmap(32,40, Pixmap.Format.RGB888);
            pixmap.setColor(Color.GRAY);
            pixmap.fill();
            Animation sprite = new Animation(100);
            sprite.addFrame(new Texture(pixmap));
            pixmap.dispose();
            addSprite(sprite);
            */

        }
        setRotatesOnMovement(false);
        bounces = true;
        holdingPoint = new Vector3(width/2, height/2, 0);
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

        // Holding point
        float hx = position.x + holdingPoint.x;
        float hy = position.y + holdingPoint.y;

        // Center around which the holding point will be rotated
        float cx = position.x + width/2;
        float cy = position.y + height/2;

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

        // Shift the point so that it may rotate around origin.
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
}
