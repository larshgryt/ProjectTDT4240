package com.mygdx.game.listeners;


import java.util.EventObject;

public abstract class EventListener {

    public abstract void notifyEvent(EventObject e);
    public abstract void notifyError(EventObject e, String message);

}
