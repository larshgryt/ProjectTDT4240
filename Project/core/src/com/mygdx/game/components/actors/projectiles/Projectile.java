package com.mygdx.game.components.actors.projectiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.actors.Actor;
import com.mygdx.game.components.gamecomponents.VisualEffect;
import com.mygdx.game.handlers.collision.Collidable;
import com.mygdx.game.handlers.collision.CollisionBox;
import com.mygdx.game.sprites.Animation;
import com.mygdx.game.states.State;

public abstract class Projectile extends Actor {

    // A generic white rectangle projectile.

    protected VisualEffect hitEffect;

    public Projectile(int width, int height){
        Animation sprite = new Animation(100);
        //Create a white background texture
        Pixmap pixmap = new Pixmap(width,height, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        sprite.addFrame(new Texture(pixmap));
        pixmap.dispose();
        sprites.add(sprite);
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
        rotatesOnMovement = true;
        collisionBox.setCollisionMode(CollisionBox.CollisionMode.LITE);
        hitEffect = null;
    }

    public Projectile(Texture texture){
        Animation sprite = new Animation(100);
        sprite.addFrame(texture);
        sprites.add(sprite);
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
        rotatesOnMovement = true;
        collisionBox.setCollisionMode(CollisionBox.CollisionMode.LITE);
    }

    /* Returns a new instance of this class and shoots it from the given coordinates in the
        given angle at the given velocity.
     */
    public Projectile fire(float x, float y, float angle, float velocity){
        Projectile projectile = getInstance();
        projectile.setPosition(x, y);
        double theta = Math.toRadians(angle);
        projectile.setVelocity(velocity * (float)Math.cos(theta), velocity * (float) Math.sin(theta));
        return projectile;
    }

    // Returns a new instance of the projectile subclass.
    protected abstract Projectile getInstance();

    /* Code that determines projectiles effect on another actor */
    public void hit(State state, Collidable other){
        if(other instanceof Component){
            if(state.containsComponent(this) && state.containsComponent((Component)other)){
                destroy(state);
            }
        }
        else{
            if(state.containsComponent(this)){
                destroy(state);
            }
        }
        if(hitEffect != null){
            state.addComponent(hitEffect.getInstance(state));
        }
    }

    public void hit(State state, Projectile other){
        if(state.containsComponent(this) && state.containsComponent(other)){
            destroy(state);
            other.destroy(state);
        }
    }

    /* Code that will be ran when the projectile is destroyed (e.g explotion) */
    public void destroy(State state){
        state.removeComponent(this);
        this.dispose();
    }

}
