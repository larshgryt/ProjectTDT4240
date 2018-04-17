package com.mygdx.game.presenters;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GdxGame;
import com.mygdx.game.components.menucomponents.Button;
import com.mygdx.game.components.menucomponents.ImageComponent;
import com.mygdx.game.components.menucomponents.TextInputField;
import com.mygdx.game.components.menucomponents.TextLabel;
import com.mygdx.game.states.MenuState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;


public class MenuPresenter extends Presenter {

    private TextLabel errorMessage;
    private TextInputField input;

    public MenuPresenter(){
        super();
        ImageComponent logo = new ImageComponent(new Texture("logo.png"));
        logo.setWidth(logo.getWidth()/2);
        logo.setHeight(logo.getHeight()/2);
        logo.setPosition((GdxGame.WIDTH-logo.getWidth())/2, (GdxGame.HEIGHT - logo.getHeight())*7/8);

        input = new TextInputField("Username", "Guest", "");
        input.setWidth(200);
        input.setHeight(40);
        input.setPosition((GdxGame.WIDTH - input.getWidth())/2, (GdxGame.HEIGHT - input.getHeight())*1/3 );
        //input.setActionListener(new TextFieldListener());

        Button playButton = new Button(200, 40);
        playButton.setText("Play game");
        playButton.setPosition((GdxGame.WIDTH - playButton.getWidth())/2, (GdxGame.HEIGHT - playButton.getHeight())*1/6 );
        playButton.setBorderWidth(2);
        playButton.setFontColor(Color.WHITE);
        playButton.setBackgroundColor(Color.NAVY);
        playButton.setBorderColor(Color.BLUE);
        playButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                notifyEvent(new MenuState.PlayEvent(input.getText()));
            }
        });

        errorMessage = new TextLabel("");
        errorMessage.setPosition((GdxGame.WIDTH - errorMessage.getWidth())/2, (GdxGame.HEIGHT - errorMessage.getHeight())*1/12 );
        errorMessage.setColor(Color.RED);

        addComponent(logo);
        addComponent(playButton);
        addComponent(input);
        addComponent(errorMessage);
    }

    public void notifyError(EventObject e, String message){
        if(e instanceof MenuState.PlayEvent){
            input.setText(message);
            errorMessage.setText(message);
        }
    }


}
