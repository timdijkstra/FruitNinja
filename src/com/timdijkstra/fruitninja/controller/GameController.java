package com.timdijkstra.fruitninja.controller;

import com.timdijkstra.fruitninja.GameThread;
import com.timdijkstra.fruitninja.model.Game;
import com.timdijkstra.fruitninja.model.GameObject;
import com.timdijkstra.fruitninja.view.GameOverScreen;
import com.timdijkstra.fruitninja.view.GameScreen;
import com.timdijkstra.fruitninja.view.PauseScreen;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;

/**
 * Created by timhd on 26-6-2017.
 */
public class GameController {

    private WorldController worldController;
    private Game game = new Game();
    private Pane root;
    private Scene scene;
    private GameScreen gameScreen;
    private PauseScreen pauzeScreen;
    private GameOverScreen gameOverScreen;
    private GameThread gameThread;
    private MediaPlayer mediaPlayer;
    private Media backgroundSound;

    public GameController(){
        root = new Pane();
        scene = new Scene(root);

        gameThread = new GameThread(game.getGameObjects());

        worldController = new WorldController(game);

        gameScreen = new GameScreen(game);
        pauzeScreen = new PauseScreen(this , gameScreen.widthProperty(), gameScreen.heightProperty());
        gameOverScreen = new GameOverScreen(this, gameScreen.widthProperty(), gameScreen.heightProperty());

        gameScreen.getChildren().add(worldController.getWorld());

        root.getChildren().add(gameScreen);
        root.getChildren().add(pauzeScreen);
        root.getChildren().add(gameOverScreen);

        game.getGameObjects().addListener(new ListChangeListener<GameObject>() {
            @Override
            public void onChanged(Change<? extends GameObject> c) {
                c.next();
                if (c.getRemovedSize() > 0){
                    if (game.getGameObjects().size() == 0){
                        game.getGameObjects().add(GameObject.getRandom());
                    }
                }
            }
        });

        game.getGameObjects().add(GameObject.getRandom());
        gameThread.setRunning(true);

        scene.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCharacter().equals(" ") && !game.isGameOver()){
                    game.setPause(!game.getPause());
                }
            }
        });

        game.pauseProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){
                    mediaPlayer.play();
                } else {
                    mediaPlayer.pause();
                }
            }
        });

        try {
            backgroundSound = new Media(getClass().getResource("/resources/game_music.wav").toURI().toString());
            mediaPlayer = new MediaPlayer(backgroundSound);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mediaPlayer.play();


    }

    public void replay(){
        game.reset();
    }

    public Scene getScene() {
        return scene;
    }

    public Game getGame() {
        return game;
    }

}
