package com.mygdx.game.components.menucomponents;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;

import java.util.ArrayList;

public class ComponentList extends Component {

    private ArrayList<Component> components;
    private ArrayList<Component> toBeRemoved;
    private boolean vertical;
    private float ySpacing;
    private float xSpacing;

    public ComponentList(){
        super();
        components = new ArrayList<Component>();
        toBeRemoved = new ArrayList<Component>();
        vertical = true;
        ySpacing = 0;
        xSpacing = 0;
    }

    public void addComponent(Component component){
        components.add(component);
    }
    public void removeComponent(Component component){
        toBeRemoved.add(component);
    }
    public boolean contains(Component component){
        return components.contains(component);
    }
    public ArrayList<Component> getComponents(){
        return components;
    }

    public boolean isVertical() {
        return vertical;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    public float getySpacing() {
        return ySpacing;
    }

    public void setySpacing(float ySpacing) {
        this.ySpacing = ySpacing;
    }

    public float getxSpacing() {
        return xSpacing;
    }

    public void setxSpacing(float xSpacing) {
        this.xSpacing = xSpacing;
    }

    @Override
    public float getWidth(){
        if(components == null){
            return 0;
        }
        float width = 0;
        float x = 0;
        if(vertical){
            for(Component c: components){
                if(c.getWidth() > width){
                    width = c.getWidth();
                }
                x += xSpacing;
            }
        }
        else{
            for(Component c: components){
                width += c.getWidth();
                x += xSpacing;
            }
        }
        return width + x;
    }

    @Override
    public float getHeight(){
        if(components == null){
            return 0;
        }
        float height = 0;
        float y = 0;
        if(!vertical){
            for(Component c: components){
                if(c.getHeight() > height){
                    height = c.getHeight();
                }
                y += ySpacing;
            }
        }
        else{
            for(Component c: components){
                height += c.getHeight();
                y += ySpacing;
            }
        }
        return height + y;
    }

    @Override
    public void update(float dt){
        super.update(dt);
        float x = 0;
        float y = 0;
        for(Component c: components){
            c.setPosition(position.x + x, position.y + y);
            c.update(dt);
            if(vertical){
                x += xSpacing;
                y -= (c.getHeight() + ySpacing);
            }
            else{
                y -= ySpacing;
                x += c.getWidth() + xSpacing;
            }
        }
        components.removeAll(toBeRemoved);
    }

    @Override
    public void render(SpriteBatch sb) {
        for(Component c: components){
            c.render(sb);
        }

    }

    @Override
    public void dispose() {
        for(Component c: components){
            c.dispose();
        }
    }
}
