package com.mygdx.game.components.menucomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.components.Component;

/*HealthBar component for showing how much health the player has*/

public class HealthBar extends Component {
    private ShapeRenderer shapeRenderer;
    private Color color;
    private Color borderColor;
    private float fillWidth;
    private float fillHeight;
    private float totalFillWidth;


    public HealthBar(float width, float height){
        super();
        shapeRenderer = new ShapeRenderer();
        this.width = width;
        this.height = height;
        fillWidth = width -2;
        fillHeight = height -2;
        totalFillWidth = fillWidth;

    }

    public void changeHealth(float currentHealth, float totalHealth){
        fillWidth = currentHealth/totalHealth * totalFillWidth;
    }

    public void setBorderColor(Color color){
        borderColor = color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void render(SpriteBatch sb){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(borderColor);
        shapeRenderer.rect(position.x, position.y, width, height);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
        shapeRenderer.rect(position.x+1, position.y+1, fillWidth, fillHeight);
        shapeRenderer.end();
    }

    public void dispose(){
        shapeRenderer.dispose();
    }

}
