package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GdxGame;
import com.mygdx.game.audio.AudioService;
import com.mygdx.game.components.menucomponents.Button;
import com.mygdx.game.components.menucomponents.Grid;
import com.mygdx.game.components.menucomponents.ImageButton;
import com.mygdx.game.components.menucomponents.ImageComponent;
import com.mygdx.game.components.menucomponents.TextInputField;
import com.mygdx.game.components.menucomponents.TextLabel;
import com.mygdx.game.components.actors.PlayerActor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MenuState extends State {

    private String username;
    private TextLabel errorMessage;
    AudioService audioService = new AudioService();

    List<ImageButton> weaponTextures = new ArrayList<ImageButton>();
    Grid weaponGrid;
    ImageButton weaponButton;
    ImageButton bazookaButton;
    ImageButton snowballButton;


    public MenuState() {
        super();
        ImageComponent logo = new ImageComponent(new Texture("logo.png"));
        logo.setWidth(logo.getWidth()/2);
        logo.setHeight(logo.getHeight()/2);
        logo.setPosition((GdxGame.WIDTH-logo.getWidth())/2, (GdxGame.HEIGHT - logo.getHeight())*7/8);

        TextInputField input = new TextInputField("Username", "Guest", "");
        input.setWidth(200);
        input.setHeight(40);
        input.setPosition((GdxGame.WIDTH - input.getWidth())/2, (GdxGame.HEIGHT - input.getHeight())*1/3 );
        input.setActionListener(new TextFieldListener());

        Button playButton = new Button(200, 40);
        playButton.setText("Play game");
        playButton.setPosition((GdxGame.WIDTH - playButton.getWidth())/2, (GdxGame.HEIGHT - playButton.getHeight())*1/6 );
        playButton.setBorderWidth(2);
        playButton.setFontColor(Color.WHITE);
        playButton.setBackgroundColor(Color.NAVY);
        playButton.setBorderColor(Color.BLUE);
        playButton.addActionListener(new ButtonListener());

        errorMessage = new TextLabel("");
        errorMessage.setPosition((GdxGame.WIDTH - errorMessage.getWidth())/2, (GdxGame.HEIGHT - errorMessage.getHeight())*1/12 );
        errorMessage.setColor(Color.RED);

        addComponent(logo);
        addComponent(playButton);
        addComponent(input);
        addComponent(errorMessage);

        username = "Guest";

        audioService.create();
    }

    private boolean isValid(String username){
        return(username.length() >= 3 && username.length() < 32);
    }

    @Override
    public void handleInput() {

    }

    private class TextFieldListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            username = actionEvent.getActionCommand();
        }
    }
    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(isValid(username)){
                errorMessage.setText("");
                System.out.println("Username " + username + " entered.");
            }
            else{
                errorMessage.setText("Invalid username was entered.");
            }

        }
    }



}
