package com.timdijkstra.fruitninja.view;

import com.timdijkstra.fruitninja.controller.WorldController;
import com.timdijkstra.fruitninja.model.GameObject;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;

/**
 * Created by timhd on 26-6-2017.
 */
public class World extends Pane {

    public static int WIDTH = 500;
    public static int HEIGHT = 500;

    public World(WorldController worldController) {
        setMinHeight(HEIGHT);
        setMaxHeight(HEIGHT);
        setMinWidth(WIDTH);
        setMaxWidth(WIDTH);

        setClip(new Rectangle(WIDTH, HEIGHT));

        setBackground(new Background(new BackgroundImage(new Image("resources/background.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        getChildren().addListener(new ListChangeListener<Node>() {
            @Override
            public void onChanged(Change<? extends Node> c) {
                c.next();
                if (c.getRemovedSize() > 0){
                    for (Node node : c.getRemoved()) {
                        worldController.removeGameObject((com.timdijkstra.fruitninja.view.GameObject) node);
                    }
                }
            }
        });
    }

    public com.timdijkstra.fruitninja.view.GameObject addGameObject(GameObject o) {
        com.timdijkstra.fruitninja.view.GameObject gameObject = null;
        switch (o.getType()) {
            case FRUIT:
                gameObject = new Fruit(o);
                break;
            case BOMB:
                gameObject = new Bomb(o);

        }
        if (gameObject != null) {
            getChildren().add(gameObject);
            return gameObject;
        }
        return null;
    }
}
