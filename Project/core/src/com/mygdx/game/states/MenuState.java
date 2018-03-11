package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.components.menucomponents.Button;

public class MenuState extends State {
    public MenuState() {
        super();
        Button button = new Button(200, 60);
        button.setText("Hello world");
        button.setPosition(100, 100);
        button.setBorderWidth(5);
        button.setFontColor(Color.BLUE);
        addComponent(button);
    }

    @Override
    public void handleInput() {

    }
}
