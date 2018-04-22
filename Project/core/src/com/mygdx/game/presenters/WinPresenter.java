package com.mygdx.game.presenters;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
            TextLabel label = new TextLabel(++playerPlacement + ": "+player.getUsername());
            playerList.addComponent(label);
        }
        addComponent(playerList);

        TextLabel gameOver = new TextLabel("Game is over");
        gameOver.setPosition((GdxGame.WIDTH - gameOver.getWidth())/2,
                GdxGame.HEIGHT/2 + gameOver.getHeight()*3);
        addComponent(gameOver);


    }

    public class PlayerList extends ComponentList {
        private TextLabel ScorePlayers;
        public PlayerList(){
            super();
            setySpacing(10);
            setVertical(true);
            ScorePlayers = new TextLabel("Scoreboard:");
            addComponent(ScorePlayers);
        }
        @Override
        public void update(float dt){
            super.update(dt);
            setPosition((GdxGame.WIDTH - getWidth() )/2, (GdxGame.HEIGHT)/2);
        }
    }
}
