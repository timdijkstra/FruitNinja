package com.timdijkstra.fruitninja.controller;

import com.timdijkstra.fruitninja.model.Game;
import com.timdijkstra.fruitninja.model.GameObject;
import com.timdijkstra.fruitninja.model.Player;
import com.timdijkstra.fruitninja.view.Bomb;
import com.timdijkstra.fruitninja.view.Fruit;
import com.timdijkstra.fruitninja.view.World;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by timhd on 26-6-2017.
 */
public class WorldController {

    private World world;
    private Player player;
    private ObservableList<GameObject> gameObjects;
    private HashMap<com.timdijkstra.fruitninja.view.GameObject, GameObject> gameObjectHashMap = new HashMap<>();
    private Media slash;
    private MediaPlayer mediaPlayer;

    public WorldController(Game game){
        this.gameObjects = game.getGameObjects();
        this.player = game.getPlayer();
        world = new World(this);

        try {
            slash = new Media(getClass().getResource("/resources/slash.wav").toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        gameObjects.addListener(new ListChangeListener<GameObject>() {
            @Override
            public void onChanged(Change<? extends GameObject> c) {
                c.next();
                if(c.getAddedSize() > 0 ){
                    for (GameObject gameObject : c.getAddedSubList()) {
                        gameObjectHashMap.put(world.addGameObject(gameObject), gameObject);
                    }
                }
            }
        });

        world.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!game.isGameOver()){
                    ArrayList<Node> toRemove = new ArrayList<>();
                    for (Node node : world.getChildren()) {
                        if(node.getBoundsInParent().contains(world.sceneToLocal(event.getSceneX(), event.getSceneY()))){
                            playMedia(slash);
                            toRemove.add(node);
                        }
                    }
                    if (toRemove.size() > 0){
                        for (Node node : toRemove) {
                            if (node instanceof Fruit){
                                player.addScore(gameObjectHashMap.get(node).getScore());
                            }
                            else if (node instanceof Bomb){
                                player.substractLives(1);
                            }
                            world.getChildren().remove(node);
                        }
                    }
                }
            }
        });
    }

    public void removeGameObject(com.timdijkstra.fruitninja.view.GameObject gameObject){
        gameObjects.remove(gameObjectHashMap.get(gameObject));
    }

    public void playMedia(Media sound){
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public World getWorld() {
        return world;
    }
}
