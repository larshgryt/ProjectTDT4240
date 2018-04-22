package com.mygdx.game.components.menucomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.components.Component;

/*HealthBar component for showing how much health the player has*/

public class HealthBar extends Component {
    private ShapeRenderer shapeRenderer;
    private float fillWidth;
    private float totalFillWidth;
    private Texture texture;
    private Texture borderTexture;
    private int borderWidth;


    public HealthBar(float width, float height){
        super();
        texture = null;
        borderTexture = null;
        shapeRenderer = new ShapeRenderer();
        this.width = width;
        this.height = height;
        fillWidth = width;
        totalFillWidth = fillWidth;
        setColor(Color.RED);
        setBorderColor(Color.BLACK);
        borderWidth = 1;
    }

    public void changeHealth(float currentHealth, float totalHealth){
        fillWidth = currentHealth/totalHealth * totalFillWidth;
    }

    public void setBorderColor(Color color){
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        pixmap.setColor(color);
        pixmap.fill();
        borderTexture = new Texture(pixmap);
        pixmap.dispose();
    }

    public void setColor(Color color){
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        pixmap.setColor(color);
        pixmap.fill();
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    public void render(SpriteBatch sb){
        sb.draw(borderTexture, position.x, position.y,
                width + 2*borderWidth, height+2*borderWidth);
        sb.draw(texture, position.x + borderWidth, position.y + borderWidth,
                fillWidth, height);
    }

    public void dispose(){
        shapeRenderer.dispose();
    }

}
