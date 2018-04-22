package com.mygdx.game.components.menucomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;
import com.mygdx.game.sprites.ColoredField;

/*HealthBar component for showing how much health the player has*/

public class HealthBar extends Component {
    private float fillWidth;
    private float totalFillWidth;
    private ColoredField background;
    private ColoredField border;
    private int borderWidth;


    public HealthBar(float width, float height){
        super();
        background = null;
        border= null;
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
        border = new ColoredField(color);
    }

    public void setColor(Color color){
        background = new ColoredField(color);
    }

    public void render(SpriteBatch sb){
        border.render(sb, position.x, position.y,
                width + borderWidth*2, height + borderWidth*2, 0);
        background.render(sb, position.x + borderWidth, position.y + borderWidth,
                fillWidth, height, 0);
    }

    public void dispose(){
        background.dispose();
        border.dispose();
    }

}
