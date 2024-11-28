package com.restaurant.simulator.controllers;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CustomerController {
    private Entity dinerEntity;

    public CustomerController(String dinerName) {
        Circle dinerCircle = new Circle(10, Color.BLUE);
        dinerEntity = FXGL.entityBuilder()
                .at(50, 550) // Posición inicial fuera del restaurante
                .view(dinerCircle)
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
        FXGL.animationBuilder()
                .duration(javafx.util.Duration.seconds(2))
                .translate(dinerEntity)
                .to(new javafx.geometry.Point2D(800, 550)) // Punto fuera de la pantalla
                .buildAndPlay();
    }
}
