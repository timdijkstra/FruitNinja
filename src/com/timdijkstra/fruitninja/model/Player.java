package com.timdijkstra.fruitninja.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by timhd on 26-6-2017.
 */
public class Player {
    private IntegerProperty score = new SimpleIntegerProperty(0);

    public int getLives() {
        return lives.get();
    }

    public IntegerProperty livesProperty() {
        return lives;
    }

    public void substractLives(int amount){
        this.lives.set(this.lives.get()-amount);
    }

    public void setLives(int lives) {
        this.lives.set(lives);
    }

    private IntegerProperty lives = new SimpleIntegerProperty(3);

    public int getScore() {
        return score.get();
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

    public void addScore(int amount){
        this.score.set(this.score.get()+amount);
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    public void reset(){
        setScore(0);
        setLives(3);
    }
}
