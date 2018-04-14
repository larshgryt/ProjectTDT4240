package com.mygdx.game.components.gamecomponents;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.actors.projectiles.Projectile;
import com.mygdx.game.sprites.Animation;
import com.mygdx.game.sprites.Sprite;

public abstract class Weapon extends Component {

    private Projectile projectile;  // Projectile fired by this weapon.
    private Sprite sprite;
    protected float originX;    // The x-coordinate which will be placed in the holding point.
    protected float originY;    // The y-coordinate which will be placed in the holding point.
    protected float aimAngle;   // The angle at which the weapon is aimed, relative to its original angle.
    protected Vector3 projectilePoint;  // The point on the weapon where the projectile comes out of.
    protected float projectileVelocity;    // The velocity of the fired projectile.

    public Weapon(int width, int height, Projectile projectile){
        Animation sprite = new Animation(100);
        //Create a white texture
        Pixmap pixmap = new Pixmap(width,height, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        sprite.addFrame(new Texture(pixmap));
        pixmap.dispose();
        this.sprite = sprite;
        this.projectile = projectile;
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
        projectilePoint = new Vector3(0, 0, 0);
        projectileVelocity = 500;
    }

    // Sets the aiming angle relative to the players own angle.
    public void setAim(float angle){
        this.aimAngle = (float)Math.toRadians(angle);
    }
    public float getAim(){
        return (float)Math.toDegrees(aimAngle);
    }

    public void setSprite(Sprite sprite){
        this.sprite = sprite;
    }
    public void setProjectileVelocity(float velocity){
        this.projectileVelocity = velocity;
    }
    public float getProjectileVelocity(){
        return projectileVelocity;
    }

    public float getOriginX(){
        return originX;
    }
    public void setOriginX(float originX){
        this.originX = originX;
    }
    public float getOriginY(){
        return originY;
    }
    public void setOriginY(float originY){
        this.originY = originY;
    }

    public void setProjectile(Projectile projectile){
        this.projectile = projectile;
    }

    public Projectile shoot(){

        // Fires a projectile instance from the projectile point at the aimed angle.

        float px = position.x;  // Used to rotate projectile point to match weapon angle.
        float py = position.y;  // Used to rotate projectile point to match weapon angle.
        float cx = position.x;  // Center around which projectile point will be rotated.
        float cy = position.y;  // Center around which projectile point will be rotated.
        float x;                // The rotated point will be stored here.
        float y;                // The rotated point will be stored here.
        float a = 1;            // Multiplier used to calculate angle depending on if the weapon is flipped.
        double theta;           // How many radians the projectile point will be rotated.


        // Shift projectile point depending on whether the weapon is flipped.
        if(horizontalFlip){
            px += originX - projectilePoint.x;
            if(!verticalFlip){
                a *= -1;
            }
        }
        else{
            px += projectilePoint.x - originX;
        }
        if(verticalFlip){
            py += originY - projectilePoint.y;
            if(!horizontalFlip){
                a *= -1;
            }
        }
        else{
            py += projectilePoint.y - originY;
        }

        // Shift projectile point to rotate around the origin.
        px -= cx;
        py -= cy;

        theta = angle + aimAngle * a;

        // Rotate points around the origin.
        x = px * (float) Math.cos(theta) - py * (float) Math.sin(theta);
        y = px * (float) Math.sin(theta) + py * (float) Math.cos(theta);

        // Shift back to original center point.
        x += cx;
        y += cy;

        float alpha = angle + aimAngle;     // The angle of the fired projectile's velocity.
        if(horizontalFlip && verticalFlip){
            alpha = (float)Math.toRadians(180) + angle + aimAngle;
        }
        else if(horizontalFlip && !verticalFlip){
            alpha = (float)Math.toRadians(180) - angle - aimAngle;
        }
        else if(verticalFlip && !horizontalFlip){
            alpha = -1*angle - aimAngle;
        }

        return projectile.fire(x, y, (float)Math.toDegrees(alpha), projectileVelocity);
    }

    // Sets the projectile point relative to the weapons position.
    public void setProjectilePoint(float x, float y){
        this.projectilePoint.set(x, y, 0);
    }
    public Vector3 getProjectilePoint(){
        return projectilePoint;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(sprite != null){

            // Properties of the sprite to be rendered will be stored in these variables.
            float a = angle;
            float x = position.x;
            float y = position.y;
            float w = width;
            float h = height;
            float a1 = 1;           // Multiplier for the aim angle.
            float ox = originX;     // The origin point around which the sprite will rotate.
            float oy = originY;     // The origin point around which the sprite will rotate.

            // Shift points and adjult multipliers if the weapon is flipped.
            if(horizontalFlip){
                w *= -1;
                x += originX;
                ox *= -1;
                a1 *= -1;
            }
            else{
                x -= originX;
            }
            if(verticalFlip){
                h *= -1;
                y += originY;
                oy *= -1;
                a1 *= -1;
            }
            else{
                y -= originY;
            }
            a += aimAngle * a1;

            // Sprite is not flipped as this is done by negative size dimensions.
            sprite.setVerticalFlip(false);
            sprite.setHorizontalFlip(false);

            sprite.render(sb, x, y, w, h, a, ox, oy);
        }
    }

    @Override
    public void update(float dt){
        super.update(dt);
        sprite.update(dt);
    }

    @Override
    public void dispose() {
        sprite.dispose();
        projectile.dispose();
    }
}
