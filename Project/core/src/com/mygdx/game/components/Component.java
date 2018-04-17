package com.mygdx.game.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.handlers.collision.BoundingBox;

import java.awt.Event;

public abstract class Component {

    /* Standard class for containing the logic of items that will be rendered on screen.*/

    protected Vector3 position;
    protected float width;
    protected float height;
    protected float angle;
    protected BoundingBox boundingBox;
    protected boolean verticalFlip;
    protected boolean horizontalFlip;
    protected Vector3 rotationPoint; // Rotation point relative to the components coordinates

    public Component(){
        position = new Vector3(0, 0, 0);
        width = 0;
        height = 0;
        angle = 0;
        boundingBox = new BoundingBox(this);
        verticalFlip = false;
        horizontalFlip = false;
        rotationPoint = new Vector3(0, 0,0);
    }

    public boolean isVerticalFlip() {
        return verticalFlip;
    }

    public void setVerticalFlip(boolean verticalFlip) {
        this.verticalFlip = verticalFlip;
    }

    public boolean isHorizontalFlip() {
        return horizontalFlip;
    }

    public void setHorizontalFlip(boolean horizontalFlip) {
        this.horizontalFlip = horizontalFlip;
    }

    public void setAngle(float angle){
        this.angle = (float) Math.toRadians(angle);
    }
    public float getAngle(){
        return (float)Math.toDegrees(angle);
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
    public void addPosition(float x, float y){
        this.position.set(position.x + x, position.y + y, 0);
    }
    public void setPosition(Vector3 position){
        this.position = position;
    }
    public void setPosition(float x, float y){
        position.set(x, y, 0);
    }
    public Vector3 getPosition(){
        return position;
    }
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }
    public void update(float dt){
        boundingBox.update();
    }
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
    public void handleError(String errorMessage){

    }
}
