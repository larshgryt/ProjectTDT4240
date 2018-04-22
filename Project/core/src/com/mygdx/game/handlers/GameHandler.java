package com.mygdx.game.handlers;


import com.mygdx.game.GdxGame;
import com.mygdx.game.components.actors.PlayerActor;
import com.mygdx.game.components.actors.projectiles.Projectile;
import com.mygdx.game.states.GameState;
import com.mygdx.game.states.GameStateManager;

import java.util.ArrayList;
import java.util.Stack;

public class GameHandler{

    private static GameHandler instance = new GameHandler();

    private ArrayList<PlayerActor> players;
    private Stack<PlayerActor> finishedPlayers;
    private PlayerActor activePlayer;
    private int turnCount;
    private boolean gameFinished;
    private float dx;
    private float dy;
    private boolean enabled;
    private Projectile activeProjectile;

    private GameHandler() {
        this.players = new ArrayList<PlayerActor>();
        this.finishedPlayers = new Stack<PlayerActor>();
        this.turnCount = 0;
        this.gameFinished = false;
        dx = 0;
        dy = 0;
        enabled = true;
        activeProjectile = null;
    }

    public static GameHandler getInstance(){
        return instance;
    }

    public ArrayList<PlayerActor> getPlayers() {
        return players;
    }

    public PlayerActor getActivePlayer() {
        return activePlayer;
    }

    public void addPlayer(PlayerActor player){
        player.playerNumber = players.size();
        this.players.add(player);
    }
    public void removePlayer(PlayerActor player){
        this.players.remove(player);
        turnCount--;
    }

    public void nextTurn(){
        if (this.players.size() == 1){
            // Victory stuff
            this.finishedPlayers.add(players.get(0));
            this.gameFinished = true;
        }
        if(turnCount >= players.size() - 1){
            turnCount = 0;
        }
        else{
            turnCount++;
        }
        activePlayer = getPlayers().get(turnCount);
    }
    public void damagePlayer(float damage, PlayerActor player){
        player.setHealth(player.getHealth() - damage);
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    public Projectile getActiveProjectile(){
        return activeProjectile;
    }
    public void setActiveProjectile(Projectile projectile){
        enabled = (projectile == null);
        activeProjectile = projectile;
    }

    public void fireWeapon(){
        if(enabled){
            if(GameStateManager.getInstance().getActiveState() instanceof GameState){
                GameState gameState = (GameState) GameStateManager.getInstance().getActiveState();
                if(activePlayer.isShooting()){
                    activePlayer.setShooting(false);
                    activeProjectile = activePlayer.getWeapon().shoot();
                    activeProjectile.setVelocity(dx * 2, dy * 2);
                    gameState.addComponent(activeProjectile);
                }
            }
        }
    }

    public void playerAim(float x, float y){
        if(enabled){
            activePlayer.setShooting(true);
            dx = x - activePlayer.getPosition().x;
            dy = (GdxGame.HEIGHT - y) - activePlayer.getPosition().y;
            float angle = (float)Math.toDegrees(Math.atan(dy/dx));
            if(dx < 0){
                activePlayer.setHorizontalFlip(true);
                angle *= -1;
            }
            else{
                activePlayer.setHorizontalFlip(false);
            }
            activePlayer.setAimAngle(angle);
        }
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
        if(enabled){
            int velocity;
            if (direction) velocity = 100;
            else velocity = -100;

            this.activePlayer.setVelocity(velocity,0);
            this.activePlayer.setMoving(true);
        }
    }

    // Stops player movement. Call on touch stop
    public void stopMove(){
        if (getActivePlayer().isMoving()){
            getActivePlayer().setMoving(false);
        }
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public Stack<PlayerActor> getFinishedPlayers() {
        return finishedPlayers;
    }
}
