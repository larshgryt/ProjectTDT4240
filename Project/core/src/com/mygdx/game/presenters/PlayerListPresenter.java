package com.mygdx.game.presenters;


import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.GdxGame;
import com.mygdx.game.components.Component;
import com.mygdx.game.components.actors.PlayerActor;
import com.mygdx.game.components.menucomponents.ComponentList;
import com.mygdx.game.components.menucomponents.HealthBar;
import com.mygdx.game.components.menucomponents.TextLabel;
import com.mygdx.game.handlers.GameHandler;

import java.util.ArrayList;

public class PlayerListPresenter extends Presenter {

    private PlayerList playerList;

    public PlayerListPresenter(ArrayList<PlayerActor> players){
        playerList = new PlayerList(players);
        addComponent(playerList);
        for(PlayerActor player: players){
            addComponent(new UsernameLabel(player));
        }
    }

    private class PlayerList extends ComponentList{
        public PlayerList(ArrayList<PlayerActor> players){
            setPosition(0, GdxGame.HEIGHT - 10);
            setySpacing(10);
            setVertical(true);
            for(PlayerActor player: players){
                addComponent(new PlayerListItem(player));
            }
        }
        public PlayerListItem getItem(PlayerActor player){
            for(Component c: getComponents()){
                if(((PlayerListItem)c).getPlayer() == player){
                    return (PlayerListItem)c;
                }
            }
            return null;
        }
    }

    private class PlayerListItem extends ComponentList{
        private PlayerActor player;
        private TextLabel username;
        private HealthBar healthBar;
        public PlayerListItem(PlayerActor player){
            this.player = player;
            username = new TextLabel(player.getUsername());
            healthBar = new HealthBar(100, 5);
            setVertical(true);
            addComponent(username);
            addComponent(healthBar);
        }
        public PlayerActor getPlayer(){
            return player;
        }
        public HealthBar getHealthBar(){
            return healthBar;
        }

        @Override
        public void update(float dt){
            super.update(dt);
            if(GameHandler.getInstance().getActivePlayer() == player){
                username.setColor(Color.BLACK);
            }
            else{
                username.setColor(Color.GRAY);
            }
            healthBar.changeHealth(player.getHealth(), player.getMaxHealth());
        }
    }

    private class UsernameLabel extends TextLabel{
        private PlayerActor player;
        public UsernameLabel(PlayerActor player){
            super(player.getUsername());
            this.player = player;
        }
        public PlayerActor getPlayer(){
            return player;
        }
        @Override
        public void update(float dt){
            super.update(dt);
            setPosition(player.getPosition().x - this.getWidth()/2,
                    player.getPosition().y + player.getHeight() + this.getHeight());
            if(GameHandler.getInstance().getActivePlayer() == player){
                setColor(Color.BLACK);
            }
            else{
                setColor(Color.GRAY);
            }
        }
    }

}
