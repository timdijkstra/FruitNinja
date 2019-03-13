package com.timdijkstra.fruitninja.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;

/**
 * Created by timhd on 26-6-2017.
 */
public class GameObject extends ImageView {

    public GameObject(String url, com.timdijkstra.fruitninja.model.GameObject gameObject){
        super(url);

        xProperty().bind(gameObject.xProperty());
        yProperty().bind(gameObject.yProperty());

        xProperty().addListener(changeListener());
        yProperty().addListener(changeListener());
    }

    private ChangeListener<Number> changeListener(){
        return new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                update();
            }
        };
    }

    private void update(){
        if (!getParent().getLayoutBounds().intersects(getBoundsInParent())){
            xProperty().unbind();
            yProperty().unbind();
            ((World)getParent()).getChildren().remove(this);
        }
    }
}
