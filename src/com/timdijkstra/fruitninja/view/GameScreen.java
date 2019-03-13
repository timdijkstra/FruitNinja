package com.timdijkstra.fruitninja.view;

import com.timdijkstra.fruitninja.model.Game;
import javafx.scene.layout.VBox;

/**
 * Created by timhd on 26-6-2017.
 */
public class GameScreen extends VBox {

    private ScoreBoard scoreBoard;

    public GameScreen(Game game){
        scoreBoard = new ScoreBoard(game.getPlayer());

        getChildren().add(scoreBoard);
    }
}
