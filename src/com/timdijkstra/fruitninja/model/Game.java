package com.timdijkstra.fruitninja.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by timhd on 26-6-2017.
 */
public class Game {
    private BooleanProperty pause = new SimpleBooleanProperty(false);
    private ObservableList<GameObject> gameObjects = FXCollections.observableArrayList();
    private Player player = new Player();

    public boolean isGameOver(){
        return player.getLives() <= 0;
    }

    public void reset(){
        player.reset();
        setPause(false);
    }

    public ObservableList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean getPause() {
        return pause.get();
    }

    public BooleanProperty pauseProperty() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause.set(pause);
    }
}
