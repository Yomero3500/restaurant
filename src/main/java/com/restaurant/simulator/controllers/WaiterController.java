package com.restaurant.simulator.controllers;

import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class WaiterController {
    private String name;
    private Circle waiterView;

    public WaiterController(String name) {
        this.name = name;
        this.waiterView = new Circle(15, Color.BLUE); // Representación gráfica
        FXGL.getGameWorld().addEntity(
                FXGL.entityBuilder()
                        .at(100, 100) // Posición inicial del mesero
                        .view(waiterView)
                        .build()
        );
    }

    public void moveToKitchen() {
        FXGL.runOnce(() -> waiterView.setTranslateX(200), FXGL.seconds(1));
        FXGL.runOnce(() -> waiterView.setTranslateY(300), FXGL.seconds(2));
    }

    public void moveToTable(int x, int y) {
        FXGL.runOnce(() -> waiterView.setTranslateX(x), FXGL.seconds(1));
        FXGL.runOnce(() -> waiterView.setTranslateY(y), FXGL.seconds(2));
    }

    public void deliverOrder(String order) {
        System.out.println(name + " está entregando la orden: " + order);
        FXGL.runOnce(() -> waiterView.setFill(Color.GREEN), FXGL.seconds(0.5));
        FXGL.runOnce(() -> waiterView.setFill(Color.BLUE), FXGL.seconds(1.5));
    }
}
