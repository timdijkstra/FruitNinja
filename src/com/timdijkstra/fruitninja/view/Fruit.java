package com.timdijkstra.fruitninja.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

/**
 * Created by timhd on 26-6-2017.
 */
public class Fruit extends GameObject {

    private Type type;
    private static Random random = new Random();

    public Fruit(com.timdijkstra.fruitninja.model.GameObject gameObject){
        super(gameObject.getFruitType().getImage(), gameObject);
        this.type = gameObject.getFruitType();
    }

    public enum Type{
        APPLE, ORANGE, STRAWBERRY;
        public String getImage(){
            switch(this){
                case APPLE:
                    return "resources/apple.png";
                case ORANGE:
                    return "resources/orange.png";
                case STRAWBERRY:
                    return "resources/strawberry.png";
                default: return "";
            }
        }
        public static Type getRandom(){
            return Type.values()[(random.nextInt(Type.values().length))];
        }
    }
}
