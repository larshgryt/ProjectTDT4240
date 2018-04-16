package com.mygdx.game.handlers;


import com.mygdx.game.components.actors.PlayerActor;

import java.util.ArrayList;

public class GameHandler extends Handler {
    private ArrayList<PlayerActor> players;
    private PlayerActor activePlayer;
    private int playerNumber;
    private int turnCount;

    public GameHandler() {
        this.players = new ArrayList<PlayerActor>();
        this.turnCount = 0;
    }

    public ArrayList<PlayerActor> getPlayers() {
        return players;
    }

    public PlayerActor getActivePlayer() {
        return activePlayer;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void addPlayer(PlayerActor player){
        this.players.add(player);
    }
    public void removePlayer(PlayerActor player){
        this.players.remove(player);
    }

    public void nextTurn(){
        if ((getPlayerNumber() + 1) == players.size()){
            this.activePlayer = players.get(0);
            this.playerNumber = 0;
        } else {
            this.activePlayer = players.get(getPlayerNumber() + 1);
            this.playerNumber = getPlayerNumber() + 1;
        }
        turnCount++;
    }
    public void damagePlayer(float damage, int number){
        float pHealth = players.get(number).getHealth();
        if ((pHealth - damage) <= 0){
            this.players.remove(number);
        }
        else players.get(number).setHealth(pHealth - damage);
    }

    // Sets velocity on active player actor. Direction= true is right movement. False is left movement.
    // Call on movement button touch
    public void playerMove(boolean direction){
        int velocity;
        if (direction) velocity = 5;
        else velocity = -5;

        this.activePlayer.setVelocity(velocity,0);
    }

    // Stops player movement. Call on touch stop
    public void stopMove(){
        this.activePlayer.setVelocity(0,0);
    }


}
