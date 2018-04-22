package com.mygdx.game.handlers;


import com.mygdx.game.components.actors.PlayerActor;

import java.util.ArrayList;
import java.util.Stack;

public class GameHandler extends Handler {
    private ArrayList<PlayerActor> players;
    private Stack<PlayerActor> finishedPlayers;
    private PlayerActor activePlayer;
    private int turnCount;
    private int listPosition;
    private boolean gameFinished;

    public GameHandler() {
        this.players = new ArrayList<PlayerActor>();
        this.finishedPlayers = new Stack<PlayerActor>();
        this.turnCount = 0;
        this.gameFinished = false;
    }

    public ArrayList<PlayerActor> getPlayers() {
        return players;
    }

    public PlayerActor getActivePlayer() {
        return activePlayer;
    }

    public int getPlayerNumber() {
        return this.activePlayer.playerNumber;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void addPlayer(PlayerActor player){
        player.playerNumber = players.size();
        this.players.add(player);
    }
    public void removePlayer(PlayerActor player){
        this.players.remove(player);
    }

    public void nextTurn(){
        if (this.players.size() == 1){
            // Victory stuff
            this.finishedPlayers.add(players.get(0));
            this.gameFinished = true;
        }
        if (this.activePlayer == players.get(players.size() -1)){
            this.activePlayer = players.get(0);
            this.listPosition = 0;
            turnCount++;
        } else {
            this.activePlayer = players.get(this.listPosition++);
        }
    }
    public void damagePlayer(float damage, int number){
        for (PlayerActor actor : players){
            if (actor.playerNumber == number){
                float pHealth = actor.getHealth();
                if ((pHealth - damage) <= 0){
                    this.finishedPlayers.add(actor);
                    this.players.remove(actor);
                }
                else actor.setHealth(pHealth - damage);
            }
        }
    }

    // Sets velocity on active player actor. Direction= true is right movement. False is left movement.
    // Call on movement button touch
    public void playerMove(boolean direction){
        int velocity;
        if (direction) velocity = 100;
        else velocity = -100;

        this.activePlayer.setVelocity(velocity,0);
        this.activePlayer.setMoving(true);
    }

    // Stops player movement. Call on touch stop
    public void stopMove(){
        this.activePlayer.setVelocity(0,0);
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public Stack<PlayerActor> getFinishedPlayers() {
        return finishedPlayers;
    }
}
