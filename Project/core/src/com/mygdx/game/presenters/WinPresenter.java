package com.mygdx.game.presenters;

import com.mygdx.game.GdxGame;
import com.mygdx.game.components.actors.PlayerActor;
import com.mygdx.game.components.menucomponents.ComponentList;
import com.mygdx.game.components.menucomponents.TextLabel;

import java.util.Stack;

public class WinPresenter extends Presenter {

    private ComponentList playerList;

    public WinPresenter(Stack<PlayerActor> scoreList) {
        super();
        playerList = new PlayerList();
        int playerPlacement = 0;
        while(!scoreList.empty()){
            TextLabel label = new TextLabel(++playerPlacement + ": "+scoreList.pop().getUsername());
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
