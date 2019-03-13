package com.timdijkstra.fruitninja.view;

import com.timdijkstra.fruitninja.model.Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Created by timhd on 26-6-2017.
 */
public class ScoreBoard extends HBox {
    private Label scoreLabel = new Label();
    private Label livesLabel = new Label();

    public ScoreBoard(Player player) {

        setAlignment(Pos.CENTER);
        setSpacing(50);

        player.scoreProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                scoreLabel.setText(String.format("Score: %d", newValue));
            }
        });

        player.livesProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                livesLabel.setText(String.format("Lives: %d", newValue));
            }
        });

        scoreLabel.setText(String.format("Score: %d", player.getScore()));
        livesLabel.setText(String.format("Lives: %d", player.getLives()));

        getChildren().add(scoreLabel);
        getChildren().add(livesLabel);
    }


}
