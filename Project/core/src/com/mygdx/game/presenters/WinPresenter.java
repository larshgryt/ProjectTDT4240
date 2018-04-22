package com.mygdx.game.presenters;


import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.GdxGame;
import com.mygdx.game.components.actors.PlayerActor;
import com.mygdx.game.components.menucomponents.ComponentList;
import com.mygdx.game.components.menucomponents.TextLabel;

import java.util.Stack;

public class WinPresenter extends Presenter {

    private Stack<PlayerActor> players;
    private ComponentList playerList;

    public WinPresenter(Stack<PlayerActor> scoreList) {
        super();

        this.players = scoreList;
        playerList = new PlayerList();

        int playerPlacement = 0;
        for (PlayerActor player : players){
            playerPlacement++;
            addComponent(new PlayerListItem(player.getUsername(), playerPlacement));
        }
        addComponent(playerList);


    }

    public class PlayerList extends ComponentList {
        private TextLabel ScorePlayers;
        public PlayerList(){
            super();
            setySpacing(10);
            ScorePlayers = new TextLabel("Scoreboard:");
            ScorePlayers.setColor(Color.WHITE);
            ScorePlayers.setHeight(20);
            setPosition(GdxGame.WIDTH/2 + 100, GdxGame.HEIGHT/2 + 100);
            addComponent(ScorePlayers);
        }
    }

    private class PlayerListItem extends ComponentList {

        private String username;
        private TextLabel label;

        public PlayerListItem(String username, int placement) {
            this.username = username;
            setVertical(false);
            setxSpacing(25);
            label = new TextLabel(placement + ". Place: " + username);
            label.setColor(Color.WHITE);
            addComponent(label);
        }
    }
}
