package com.restaurant.simulator.controllers;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import com.restaurant.simulator.utils.SpriteLoader;

public class CustomerController {
    private Entity dinerEntity;

    public CustomerController(String dinerName) {
        Texture texture = SpriteLoader.getSprite("burger.png",1,1,32,32);
        dinerEntity = FXGL.entityBuilder()
                .at(50, 550) // Posición inicial fuera del restaurante
                .view(texture)
                .buildAndAttach();
    }

    public void moveToTable(int tableX, int tableY) {
        // Mover al comensal hacia la mesa
        FXGL.animationBuilder()
                .duration(javafx.util.Duration.seconds(2))
                .translate(dinerEntity)
                .to(new javafx.geometry.Point2D(tableX, tableY))
                .buildAndPlay();
    }

    public void exitRestaurant() {
        // Movimiento de salida del restaurante
        FXGL.animationBuilder()
                .duration(javafx.util.Duration.seconds(2))
                .translate(dinerEntity)
                .to(new javafx.geometry.Point2D(800, 550)) // Punto fuera de la pantalla
                .buildAndPlay();
    }
}
