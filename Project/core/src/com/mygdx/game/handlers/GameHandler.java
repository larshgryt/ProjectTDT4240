package com.mygdx.game.handlers;


import com.mygdx.game.GdxGame;
import com.mygdx.game.components.actors.PlayerActor;
import com.mygdx.game.components.actors.projectiles.Projectile;
import com.mygdx.game.states.GameState;
import com.mygdx.game.states.GameStateManager;

import java.util.ArrayList;
import java.util.Stack;

public class GameHandler extends Handler {
    private ArrayList<PlayerActor> players;
    private Stack<PlayerActor> finishedPlayers;
    private PlayerActor activePlayer;
    private int turnCount;
    private float dx;
    private float dy;
    private int listCounter;

    public GameHandler() {
        this.players = new ArrayList<PlayerActor>();
        this.finishedPlayers = new Stack<PlayerActor>();
        this.turnCount = 0;
        dx = 0;
        dy = 0;
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

        }
        if (this.activePlayer == players.get(players.size() -1)){
            this.activePlayer = players.get(0);
            this.listCounter = 0;
            turnCount++;
        } else {
            this.activePlayer = players.get(this.listCounter++);
        }
        this.activePlayer.setShooting(false);
        turnCount++;
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

    public void fireWeapon(){
        if(GameStateManager.getInstance().getActiveState() instanceof GameState){
            GameState gameState = (GameState) GameStateManager.getInstance().getActiveState();
            if(activePlayer.isShooting()){
                System.out.println("shooting...");
                activePlayer.setShooting(false);
                Projectile p = activePlayer.getWeapon().shoot();
                p.setVelocity(dx * 2, dy * 2);
                gameState.addComponent(p);
            }
        }
    }

    public void playerAim(float x, float y){
        activePlayer.setShooting(true);
        System.out.println("x:"+x +" y:" + y);
        dx = x - activePlayer.getPosition().x;
        dy = (GdxGame.HEIGHT - y) - activePlayer.getPosition().y;
        System.out.println("dx:"+dx+" dy:"+dy);
        float angle = (float)Math.toDegrees(Math.atan(dy/dx));
        if(dx < 0){
            activePlayer.setHorizontalFlip(true);
            angle *= -1;
        }
        else{
            activePlayer.setHorizontalFlip(false);
        }
        activePlayer.setAimAngle(angle);
        System.out.println(activePlayer.toString()+" x:"+activePlayer.getPosition().x+" y:"+
                activePlayer.getPosition().y+" angle:"+activePlayer.getAngle()+" aimAngle:"+activePlayer.getAimAngle());
    }

    public float getDx(){
        return dx;

    }
    public float getDy(){
        return dy;
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
        if (getActivePlayer().isMoving()){
            getActivePlayer().setMoving(false);
        }
    }


}
