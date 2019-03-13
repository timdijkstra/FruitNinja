package com.timdijkstra.fruitninja.view;

import com.timdijkstra.fruitninja.controller.GameController;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Created by timhd on 27-6-2017.
 */
public class PauseScreen extends VBox {

    private double height;
    private Label pauseLabel = new Label("Pauze");
    private TranslateTransition transition;

    public PauseScreen(GameController gameController, ReadOnlyDoubleProperty widthProperty, ReadOnlyDoubleProperty heightProperty) {
        setStyle("-fx-background-color: rgba(255, 255, 255, 0.5)");

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

        setAlignment(Pos.CENTER);
        getChildren().add(pauseLabel);

        gameController.getGame().pauseProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                transition = new TranslateTransition(Duration.millis(500), PauseScreen.this);
                transition.setInterpolator(Interpolator.EASE_BOTH);
                if (newValue) {
//                    transition.setToY(0);
                    PauseScreen.this.setTranslateY(0);
                    pauseLabel.setFont(new Font("Arial", 30));
                    pauseLabel.setText("Pause");
                } else {
//                    transition.setToY(height);
                    PauseScreen.this.setTranslateY(height);
                }
//                transition.play();
            }

        });
    }
}
