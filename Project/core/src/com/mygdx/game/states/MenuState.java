package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GdxGame;
import com.mygdx.game.components.menucomponents.Button;
import com.mygdx.game.components.menucomponents.ImageComponent;

public class MenuState extends State {

    private Texture logoImg;

    public MenuState() {
        super();
        ImageComponent logo = new ImageComponent(new Texture("logo.png"));
        logo.setPosition((GdxGame.WIDTH-logo.getWidth())/2, (GdxGame.HEIGHT - logo.getHeight())*7/8);
        Button button = new Button(200, 60);
        button.setText("Hello world");
        button.setPosition((GdxGame.WIDTH - button.getWidth())/2, (GdxGame.HEIGHT - button.getHeight())*3/4 );
        button.setBorderWidth(5);
        button.setFontColor(Color.BLUE);
        addComponent(button);
        addComponent(logo);
    }

    @Override
    public void handleInput() {

    }
}
