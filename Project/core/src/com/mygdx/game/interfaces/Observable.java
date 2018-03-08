package com.mygdx.game.interfaces;

/**
 * Created by agava on 08.03.2018.
 */

public interface Observable {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();

}
