package com.timdijkstra.fruitninja;

import com.timdijkstra.fruitninja.controller.GameController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;

/**
 * Created by timhd on 26-6-2017.
 */
public class Main extends Application {

    private GameController gameController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        gameController = new GameController();

        primaryStage.setScene(gameController.getScene());

        primaryStage.setResizable(false);

        primaryStage.sizeToScene();

        primaryStage.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    gameController.getGame().setPause(true);
                }
            }
        });

        primaryStage.show();
    }
}