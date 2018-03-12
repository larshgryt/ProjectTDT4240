package com.mygdx.game.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class Component {
    protected Vector3 position;
    protected float width;
    protected float height;
    protected float angle;
    public Component(){
        position = new Vector3(0, 0, 0);
        width = 0;
        height = 0;
        angle = 0;
    }
    public void setAngle(float angle){
        this.angle = angle % 360;
    }
    public float getAngle(){
        return angle;
    }
    public void setWidth(float width){
        this.width = width;
    }
    public float getWidth(){
        return width;
    }
    public void setHeight(float height){
        this.height = height;
    }
    public float getHeight(){
        return height;
    }
    public void setPosition(Vector3 position){
        this.position = position;
    }
    public void setPosition(float x, float y){
        this.position = new Vector3(x, y, 0);
    }
    public Vector3 getPosition(){
        return position;
    }


    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
