package com.mygdx.game.presenters;


import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.GdxGame;
import com.mygdx.game.components.actors.PlayerActor;
import com.mygdx.game.components.menucomponents.ComponentList;
import com.mygdx.game.components.menucomponents.TextLabel;

import java.util.Stack;

public class WinPresenter extends Presenter {

    private Stack<PlayerActor> players;

    public WinPresenter(Stack<PlayerActor> scoreList) {
        super();

        this.players = scoreList;

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
}
