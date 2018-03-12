package com.mygdx.game.components.menucomponents;

import com.mygdx.game.components.Component;
import java.util.Arrays;
import java.util.Collections;

public class BoundingBox {

    float startX;
    float startY;
    float endX;
    float endY;
    Component component;

    public BoundingBox(Component component){
        this.component = component;
    }

    public BoundingBox get(){
        update();
        return this;
    }

    private void update(){
        float x1 = component.getPosition().x;
        float y1 = component.getPosition().y;
        float x2 = component.getPosition().x + component.getWidth();
        float y2 = component.getPosition().y + component.getHeight();
        float xc = component.getPosition().x + component.getWidth()/2;
        float yc = component.getPosition().y + component.getHeight()/2;
        float theta = component.getAngle();

        Float[] x = new Float[4];
        Float[] y = new Float[4];

        float tempX = x1 - xc;
        float tempY = y1 - yc;
        float rotatedX = (float) (tempX*Math.cos(theta) - tempY*Math.sin(theta));
        float rotatedY = (float) (tempX*Math.sin(theta) + tempY*Math.cos(theta));
        x[0] = rotatedX + xc;
        y[0] = rotatedY + yc;

        tempY = y2 - yc;
        rotatedX = (float) (tempX*Math.cos(theta) - tempY*Math.sin(theta));
        rotatedY = (float) (tempX*Math.sin(theta) + tempY*Math.cos(theta));
        x[1] = rotatedX + xc;
        y[1] = rotatedY + yc;

        tempX = x2 - xc;
        rotatedX = (float) (tempX*Math.cos(theta) - tempY*Math.sin(theta));
        rotatedY = (float) (tempX*Math.sin(theta) + tempY*Math.cos(theta));
        x[2] = rotatedX + xc;
        y[2] = rotatedY + yc;

        tempY = y1 - yc;
        rotatedX = (float) (tempX*Math.cos(theta) - tempY*Math.sin(theta));
        rotatedY = (float) (tempX*Math.sin(theta) + tempY*Math.cos(theta));
        x[4] = rotatedX + xc;
        y[4] = rotatedY + yc;

        this.startX = Collections.min(Arrays.asList(x));
        this.startY = Collections.min(Arrays.asList(y));
        this.endX = Collections.max(Arrays.asList(x));
        this.endY = Collections.max(Arrays.asList(x));
    }

}
