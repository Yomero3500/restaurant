package com.restaurant.simulator.controllers;

import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CocineroController {
    private String name;
    private Rectangle chefView;

    public CocineroController(String name) {
        this.name = name;
        this.chefView = new Rectangle(30, 30, Color.RED); // Representación gráfica
        FXGL.getGameWorld().addEntity(
                FXGL.entityBuilder()
                        .at(400, 100) // Posición inicial en la cocina
                        .view(chefView)
                        .build()
        );
    }

    public void startCooking(String order) {
        System.out.println(name + " está preparando: " + order);
        chefView.setFill(Color.YELLOW);
    }

    public void finishCooking(String order) {
        System.out.println(name + " terminó de preparar: " + order);
        chefView.setFill(Color.RED);
    }
}
