package com.restaurant.simulator.controllers;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import com.restaurant.simulator.utils.SpriteLoader;
import javafx.geometry.Point2D;

public class CustomerController {
    private Entity dinerEntity;

    public CustomerController(String dinerName) {
        Texture texture = SpriteLoader.getSprite("burger.png",1,1,32,32);
        dinerEntity = FXGL.entityBuilder()
                .at(700, 550)
                .view(texture)
                .buildAndAttach();
    }

    public void moveToLobby(int tableX, int tableY) {
        FXGL.animationBuilder()
                .onFinished(()->{
                    FXGL.animationBuilder()
                            .duration(javafx.util.Duration.seconds(1))
                            .translate(dinerEntity)
                            .to(new Point2D(tableX,tableY-200))
                            .buildAndPlay();
                })
                .duration(javafx.util.Duration.seconds(1))
                .translate(dinerEntity)
                .to(new javafx.geometry.Point2D(tableX, tableY))
                .buildAndPlay();
    }

    public void moveToTable(double tableX, double tableY){
        FXGL.animationBuilder()
                .duration(javafx.util.Duration.seconds(1))
                .translate(dinerEntity)
                .to(new Point2D(tableX, tableY))
                .buildAndPlay();
    }

    public void exitRestaurant() {
        // Movimiento de salida del restaurante
        FXGL.animationBuilder()
                .onFinished(()->{
                    FXGL.animationBuilder()
                            .onFinished(()->{
                                FXGL.animationBuilder()
                                        .duration(javafx.util.Duration.seconds(1))
                                        .translate(dinerEntity)
                                        .to(new javafx.geometry.Point2D(700, -50))
                                        .buildAndPlay();
                            })
                            .duration(javafx.util.Duration.seconds(1))
                            .translate(dinerEntity)
                            .to(new javafx.geometry.Point2D(700, 300))
                            .buildAndPlay();
                })
                .duration(javafx.util.Duration.seconds(1))
                .translate(dinerEntity)
                .to(new javafx.geometry.Point2D(550, 300))
                .buildAndPlay();
    }
}
