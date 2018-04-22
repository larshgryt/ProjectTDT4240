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
    private int listPosition;
    private boolean gameFinished;
    private float dx;
    private float dy;
    private boolean enabled;
    private Projectile activeProjectile;

    public GameHandler() {
        this.players = new ArrayList<PlayerActor>();
        this.finishedPlayers = new Stack<PlayerActor>();
        this.turnCount = 0;
        this.gameFinished = false;
        dx = 0;
        dy = 0;
        enabled = true;
        activeProjectile = null;
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
        this.activePlayer.setShooting(false);
        enabled = true;
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
                break;
            }
        }
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
                    System.out.println("shooting...");
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
