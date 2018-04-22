package com.mygdx.game.components.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GdxGame;
import com.mygdx.game.components.actors.Actor;
import com.mygdx.game.components.stage.stagecomponents.Border;
import com.mygdx.game.components.stage.stagecomponents.StageComponent;
import com.mygdx.game.handlers.collision.CollisionBox;

import java.util.ArrayList;

public abstract class Stage {

    // A class containing a background and a set of rigid, non-moving objects.

    public static float DEFAULT_GRAVITY = -981;
    private ArrayList<StageComponent> stageComponents;
    private Texture backgroundTexture;
    protected float width;
    protected float height;
    private float gravity;

    public Stage(float width, float height){
        stageComponents = new ArrayList<StageComponent>();

        //Create a white background texture
        Pixmap pixmap = new Pixmap(1,1, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        backgroundTexture = new Texture(pixmap);
        pixmap.dispose();

        this.width = width;
        this.height = height;
        this.gravity = DEFAULT_GRAVITY;

        Border borderBottom = new Border((int)width + 200, 100);
        borderBottom.setPosition(-100, -100);
        Border borderTop = new Border((int)width + 200, 100);
        borderTop.setPosition(-100, height + 100);
        Border borderLeft = new Border(100, (int) height + 200);
        borderLeft.setPosition(-100, -100);
        Border borderRight = new Border(100, (int) height + 200);
        borderRight.setPosition(width, -100);

        addStageComponent(borderBottom);
        addStageComponent(borderLeft);
        addStageComponent(borderRight);
        addStageComponent(borderTop);
    }

    // Applies gravity to an actor unless it's standing on top of a solid stage component.
    public void applyGravityToActor(Actor actor){
        for(StageComponent sc: stageComponents){
            if(sc.getCollisionBox().getCollisionMode() != CollisionBox.CollisionMode.NEVER &&
                    actor.getPosition().y > sc.getPosition().y &&
                    actor.getPosition().x > sc.getPosition().x &&
                    actor.getPosition().x < sc.getPosition().x+sc.getWidth() &&
                    actor.getPosition().y < sc.getPosition().y+sc.getHeight() + 2 &&
                    Math.abs(actor.getVelocity().y) < actor.getBounceThreshold()){
                    actor.setAcceleration(actor.getAcceleration().x, 0);
                    actor.getVelocity().x = actor.getVelocity().x * sc.getFriction();
                    if(Math.abs(actor.getVelocity().y) < actor.getBounceThreshold()){
                        actor.getVelocity().y = 0;
                    }
                    return;

            }
        }
        actor.setAcceleration(actor.getAcceleration().x, getGravity() * actor.getMass());
    }

    public void setGravity(float gravity){
        this.gravity = gravity;
    }
    public float getGravity(){
        return gravity;
    }

    public void setBackgroundTexture(Texture texture){
        this.backgroundTexture = texture;
    }

    public void addStageComponent(StageComponent sc){
        stageComponents.add(sc);
    }
    public void removeStageComponent(StageComponent sc){
        stageComponents.remove(sc);
    }
    public ArrayList<StageComponent> getStageComponents(){
        return stageComponents;
    }

    public float getWidth(){
        return width;
    }
    public float getHeight(){
        return height;
    }

    public void update(float dt){
        for(StageComponent sc: stageComponents){
            sc.update(dt);
        }
    }

    public void render(SpriteBatch sb){
        sb.draw(backgroundTexture, 0, 0, width, height);
        for(StageComponent sc: stageComponents){
            sc.render(sb);
        }
    }

    public void dispose(){
        backgroundTexture.dispose();
        for(StageComponent sc: stageComponents){
            sc.dispose();
        }
    }

}
