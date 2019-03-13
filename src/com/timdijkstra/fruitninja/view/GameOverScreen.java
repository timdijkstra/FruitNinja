package com.timdijkstra.fruitninja.view;

import com.timdijkstra.fruitninja.controller.GameController;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Created by timhd on 27-6-2017.
 */
public class GameOverScreen extends VBox {

    private double height;
    private Label gameOverLabel = new Label();
    private Label scoreLabel = new Label();
    private Button replayButton = new Button("Replay");
    private TranslateTransition transition;

    public GameOverScreen(GameController gameController, ReadOnlyDoubleProperty widthProperty, ReadOnlyDoubleProperty heightProperty) {
        setStyle("-fx-background-color: rgba(255, 0, 0, 0.5)");

        prefHeightProperty().bind(heightProperty);
        prefWidthProperty().bind(widthProperty);

        heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                height = newValue.doubleValue();
                if (!gameController.getGame().getPause()) {
                    setTranslateY(height);
                }
            }
        });

        replayButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gameController.replay();
            }
        });

        setAlignment(Pos.CENTER);
        getChildren().add(gameOverLabel);
        getChildren().add(scoreLabel);
        getChildren().add(replayButton);

        gameController.getGame().getPlayer().livesProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                transition = new TranslateTransition(Duration.millis(500), GameOverScreen.this);
                transition.setInterpolator(Interpolator.EASE_BOTH);
                if (newValue.intValue() == 0) {
                    transition.setToY(0); // this
                    GameOverScreen.this.setTranslateY(0);
                    gameOverLabel.setFont(new Font("Arial", 50));
                    gameOverLabel.setText("GAME OVER");
                    scoreLabel.setFont(new Font("Arial", 30));
                    scoreLabel.setText("Je score is: " + gameController.getGame().getPlayer().getScore());
                } else {
                    transition.setToY(height); // this
                }
                transition.play(); // this
            }
        });
    }
}
