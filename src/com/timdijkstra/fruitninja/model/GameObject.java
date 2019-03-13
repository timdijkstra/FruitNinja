package com.timdijkstra.fruitninja.model;

import com.timdijkstra.fruitninja.view.Fruit;
import com.timdijkstra.fruitninja.view.World;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

/**
 * Created by timhd on 26-6-2017.
 */
public class GameObject {
    private IntegerProperty x = new SimpleIntegerProperty(0);
    private IntegerProperty y = new SimpleIntegerProperty(0);
    private Type type;
    private Fruit.Type fruitType;
    private static Random random = new Random();
    private Direction direction;
    private static int margin = 50;

    public GameObject(Type type) {
        this.type = type;
    }

    public int getX() {
        return x.get();
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public int getY() {
        return y.get();
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Fruit.Type getFruitType() {
        return fruitType;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setFruitType(Fruit.Type fruitType) {
        this.fruitType = fruitType;
    }

    public static GameObject getRandom(){
        Type type = Type.getRandom();
        GameObject gameObject = new GameObject(type);
        if (type == Type.FRUIT){
            gameObject.setFruitType(Fruit.Type.getRandom());
        }

        gameObject.setDirection(Direction.getRandom());
        switch (gameObject.getDirection()){
            case NORTH:
                gameObject.setX(random.nextInt(World.WIDTH) - margin);
                gameObject.setY(World.HEIGHT);
                break;
            case EAST:
                gameObject.setX(World.WIDTH);
                gameObject.setY(random.nextInt(World.HEIGHT) - margin);
                break;
            case SOUTH:
                gameObject.setX(random.nextInt(World.WIDTH) - margin);
                gameObject.setY(0);
                break;
            case WEST:
                gameObject.setX(0);
                gameObject.setY(random.nextInt(World.HEIGHT) - margin);
                break;
        }
        return gameObject;
    }

    public void move(){
        switch (getDirection()){
            case NORTH:
                setY(getY()-1);
                break;
            case EAST:
                setX(getX()-1);
                break;
            case SOUTH:
                setY(getY()+1);
                break;
            case WEST:
                setX(getX()+1);
                break;
        }
    }

    public Integer getScore(){
        switch(fruitType){
            case ORANGE:
            case APPLE:
                return 50;
            case STRAWBERRY:
                return 100;
            default: return 0;
        }
    }

    public enum Type{
        FRUIT, BOMB;

        public static Type getRandom(){
            return Type.values()[(random.nextInt(Type.values().length))];
        }
    }

    public enum Direction{
        NORTH, EAST, SOUTH, WEST;

        public static Direction getRandom(){
            return Direction.values()[(random.nextInt(Direction.values().length))];
        }
    }
}

