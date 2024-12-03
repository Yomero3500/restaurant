package com.restaurant.simulator.controllers;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import com.restaurant.simulator.utils.SpriteLoader;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.shape.Circle;

public class WaiterController {
    private String name;
    private Circle waiterView;
    private Entity waiterEntity;

    public WaiterController(String name) {
        this.name = name;
        Texture textureWaiter = SpriteLoader.getSprite("pan.png",1,1,32,32);
        waiterEntity= FXGL.entityBuilder()
                .at(200,400)
                .viewWithBBox(textureWaiter)
                .buildAndAttach();
    }

    public void moveToKitchen(int tableX, int tableY) {
        FXGL.animationBuilder()
                .duration(javafx.util.Duration.seconds(1))
                .translate(waiterEntity)
                .to(new Point2D(tableX, tableY))
                .buildAndPlay();
    }

    public void moveToTable(int x, int y) {
        FXGL.animationBuilder()
                .duration(javafx.util.Duration.seconds(1))
                .translate(waiterEntity)
                .to(new Point2D(x, y))
                .buildAndPlay();
    }

    public void deliverOrder(String order) {
        System.out.println(name + " está entregando la orden: " + order);
    }
}
