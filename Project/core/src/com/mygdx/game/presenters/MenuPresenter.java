package com.mygdx.game.presenters;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GdxGame;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.menucomponents.Button;
import com.mygdx.game.components.menucomponents.ComponentList;
import com.mygdx.game.components.menucomponents.ImageComponent;
import com.mygdx.game.components.menucomponents.TextInputField;
import com.mygdx.game.components.menucomponents.TextLabel;
import com.mygdx.game.listeners.EventListener;
import com.mygdx.game.states.MenuState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;


public class MenuPresenter extends Presenter {

    private TextLabel errorMessage;
    private TextInputField input;
    private ComponentList joinedPlayerList;

    public MenuPresenter(){
        super();

        joinedPlayerList = new ComponentList();
        joinedPlayerList.setySpacing(10);
        joinedPlayerList.setPosition(50, 300);
        TextLabel joinedPlayers = new TextLabel("Joined players:");
        joinedPlayers.setColor(Color.WHITE);
        joinedPlayers.setHeight(12);
        joinedPlayerList.addComponent(joinedPlayers);

        input = new TextInputField("Username", "Guest", "");
        input.setWidth(200);
        input.setHeight(40);
        input.setPosition((GdxGame.WIDTH - input.getWidth())/2, (GdxGame.HEIGHT - input.getHeight())*1/3 );


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
                ArrayList<String> usernames = new ArrayList<String>();
                for(Component c: joinedPlayerList.getComponents()){
                    if(c instanceof PlayerListItem){
                        usernames.add(((PlayerListItem) c).getUsername());
                    }
                }
                notifyEvent(new MenuState.PlayEvent(usernames));
            }
        });

        errorMessage = new TextLabel("");
        errorMessage.setPosition((GdxGame.WIDTH - errorMessage.getWidth())/2, (GdxGame.HEIGHT - errorMessage.getHeight())*1/12 );
        errorMessage.setColor(Color.RED);

        addComponent(new Logo());
        addComponent(playButton);
        addComponent(input);
        addComponent(errorMessage);
        addComponent(new AddPlayerButton());
        addComponent(joinedPlayerList);
        addEventListener(new EventListener() {
            @Override
            public void notifyEvent(EventObject e) {
                if(e instanceof MenuState.AddPlayerEvent){
                    if(((MenuState.AddPlayerEvent) e).isChecked()){
                        addPlayer((MenuState.AddPlayerEvent) e);
                    }
                }
            }

            @Override
            public void notifyError(EventObject e, String message) {
                printError(message);
            }
        });
    }

    public void addPlayer(MenuState.AddPlayerEvent e){
        for(Component c: joinedPlayerList.getComponents()){
            if(c instanceof  PlayerListItem){
                if(((PlayerListItem) c).getUsername().equals(e.getValue())){
                    printError("Username already exists.");
                    return;
                }
            }
        }
        joinedPlayerList.addComponent(new PlayerListItem(e.getValue()));
        printError("");
    }
    public void printError(String message){
        errorMessage.setText(message);
    }

    private class PlayerListItem extends ComponentList{

        private String username;

        public PlayerListItem(String username){

            this.username = username;

            setVertical(false);
            setxSpacing(25);

            TextLabel playerName = new TextLabel(username);
            playerName.setColor(Color.WHITE);
            addComponent(playerName);
            addComponent(new RemovePlayerButton(this));

        }

        public String getUsername(){
            return username;
        }
    }

    private class RemovePlayerButton extends Button{
        public RemovePlayerButton(final PlayerListItem playerListItem){
            super(20, 20);
            setText("x");
            setBorderWidth(2);
            setFontColor(Color.WHITE);
            setBackgroundColor(Color.NAVY);
            setBorderColor(Color.BLUE);
            addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println(playerListItem.getUsername()+" was removed.");
                    joinedPlayerList.removeComponent(playerListItem);
                }
            });
        }
    }

    private class AddPlayerButton extends Button{
        public AddPlayerButton(){
            super(100, 40);
            setText("Add player");
            setBorderWidth(2);
            setFontColor(Color.WHITE);
            setBackgroundColor(Color.NAVY);
            setBorderColor(Color.BLUE);
            addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    notifyEvent(new MenuState.AddPlayerEvent(input.getText()));
                }
            });
        }
    }

    private class Logo extends ImageComponent{
        public Logo(){
            super(new Texture("logo.png"));
            setWidth(getWidth()/2);
            setHeight(getHeight()/2);
            setPosition((GdxGame.WIDTH-getWidth())/2, (GdxGame.HEIGHT - getHeight())*7/8);
        }
    }


}
