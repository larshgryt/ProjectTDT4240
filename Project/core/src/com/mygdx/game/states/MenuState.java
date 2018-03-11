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

        Button playButton = new Button(200, 40);
        playButton.setText("Play game");
        playButton.setPosition((GdxGame.WIDTH - playButton.getWidth())/2, (GdxGame.HEIGHT - playButton.getHeight())*3/5 );
        playButton.setBorderWidth(2);
        playButton.setFontColor(Color.WHITE);
        playButton.setBackgroundColor(Color.NAVY);
        playButton.setBorderColor(Color.BLUE);

        Button settingsButton = new Button(200, 40);
        settingsButton.setText("Settings");
        settingsButton.setPosition((GdxGame.WIDTH - settingsButton.getWidth())/2, (GdxGame.HEIGHT - settingsButton.getHeight())*2/5 );
        settingsButton.setBorderWidth(2);
        settingsButton.setFontColor(Color.WHITE);
        settingsButton.setBackgroundColor(Color.NAVY);
        settingsButton.setBorderColor(Color.BLUE);

        Button quitButton = new Button(200, 40);
        quitButton.setText("Quit");
        quitButton.setPosition((GdxGame.WIDTH - quitButton.getWidth())/2, (GdxGame.HEIGHT - quitButton.getHeight())*1/5 );
        quitButton.setBorderWidth(2);
        quitButton.setFontColor(Color.WHITE);
        quitButton.setBackgroundColor(Color.NAVY);
        quitButton.setBorderColor(Color.BLUE);

        addComponent(logo);
        addComponent(playButton);
        addComponent(settingsButton);
        addComponent(quitButton);

    }

    @Override
    public void handleInput() {

    }
}
