package com.mygdx.game.components.gamecomponents;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.actors.projectiles.Projectile;
import com.mygdx.game.sprites.Animation;
import com.mygdx.game.sprites.Sprite;

public abstract class Weapon extends Component {

    private Projectile projectile;
    private Sprite sprite;
    protected float originX;    // The x-coordinate which will be placed in the holding point.
    protected float originY;    // The y-coordinate which will be placed in the holding point.
    protected float aimAngle;   // The angle at which the weapon is aimed, relative to its original angle.

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
    }

    public void setAim(float angle){
        this.aimAngle = (float)Math.toRadians(angle);
    }
    public float getAim(){
        return (float)Math.toDegrees(aimAngle);
    }

    public void setSprite(Sprite sprite){
        this.sprite = sprite;
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

    public abstract void fire(float angle);

    @Override
    public void render(SpriteBatch sb) {
        if(sprite != null){
            float a = angle;
            float x = position.x;
            float y = position.y;
            float w = width;
            float h = height;
            float a1 = 1;
            float ox = originX;
            float oy = originY;
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
