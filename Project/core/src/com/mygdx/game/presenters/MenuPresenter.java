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

        joinedPlayerList = new JoinedPlayerList();
        input = new UsernameField();
        errorMessage = new ErrorMessageLabel();

        addComponent(new Logo());
        addComponent(new PlayButton());
        addComponent(new AddPlayerButton());
        addComponent(input);
        addComponent(errorMessage);
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
        private Button button;
        private TextLabel label;

        public PlayerListItem(String username){
            this.username = username;
            setVertical(false);
            setxSpacing(25);
            label = new TextLabel(username);
            label.setColor(Color.WHITE);
            button = new RemovePlayerButton(this);
            addComponent(label);
            addComponent(button);

        }

        public String getUsername(){
            return username;
        }
        @Override
        public float getWidth(){
            return 90;
        }

        @Override
        public void update(float dt){
            super.update(dt);
            label.setPosition(button.getPosition().x - label.getWidth() - 4,
                    label.getPosition().y + button.getHeight()/2);
        }

    }

    private class ErrorMessageLabel extends TextLabel{
        public ErrorMessageLabel(){
            super(" ");
            setPosition((GdxGame.WIDTH - getWidth())/2, (GdxGame.HEIGHT - getHeight())*1/14 );
            setColor(Color.RED);
        }
        @Override
        public void update(float dt){
            super.update(dt);
            setPosition((GdxGame.WIDTH - getWidth())/2, getPosition().y);
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

    private class UsernameField extends TextInputField{
        public UsernameField(){
            super("Username", "Guest", "");
            setWidth(200);
            setHeight(30);
            setPosition(176, (GdxGame.HEIGHT - getHeight())*1/3 );
        }
    }

    private class AddPlayerButton extends Button{
        public AddPlayerButton(){
            super(80, input.getHeight()-1);
            setText("Add player");
            setPosition(GdxGame.WIDTH - input.getPosition().x - getWidth(), input.getPosition().y);
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

    private class PlayButton extends Button{
        public PlayButton(){
            super(200, 40);
            setText("Play game");
            setPosition((GdxGame.WIDTH - getWidth())/2, input.getPosition().y - getHeight() - 20);
            setBorderWidth(2);
            setFontColor(Color.WHITE);
            setBackgroundColor(Color.NAVY);
            setBorderColor(Color.BLUE);
            addActionListener(new ActionListener(){
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
        }
    }

    public class JoinedPlayerList extends ComponentList{
        private TextLabel joinedPlayers;
        public JoinedPlayerList(){
            super();
            setySpacing(10);
            joinedPlayers = new TextLabel("Joined players:");
            joinedPlayers.setColor(Color.WHITE);
            joinedPlayers.setHeight(20);
            setPosition(10, GdxGame.HEIGHT - 20);
            addComponent(joinedPlayers);
        }
    }

}
