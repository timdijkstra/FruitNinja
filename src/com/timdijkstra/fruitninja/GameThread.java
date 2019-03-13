package com.timdijkstra.fruitninja;

import com.timdijkstra.fruitninja.model.GameObject;
import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.util.ConcurrentModificationException;

/**
 * Created by timhd on 27-6-2017.
 */
public class GameThread extends Thread{
    private volatile boolean running = false;
    private ObservableList<GameObject> gameObjects;

    public GameThread(ObservableList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
        start();
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while(true){
            if(running){
                try {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                for (GameObject gameObject : gameObjects) {
                                    gameObject.move();
                                }
                            } catch(ConcurrentModificationException e){
                                //ignore (meerdere GameObjects)
                            }

                        }
                    });
                    Thread.sleep(6);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
