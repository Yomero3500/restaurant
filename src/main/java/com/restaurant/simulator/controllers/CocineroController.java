package com.restaurant.simulator.controllers;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import com.restaurant.simulator.utils.SpriteLoader;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CocineroController {
    private String name;
    private Rectangle chefView;
    private Entity entityChef;

    public CocineroController(String name) {
        this.name = name;
        Texture textureChef = SpriteLoader.getSprite("mapache.png",1,1,32,32);
        entityChef = FXGL.entityBuilder()
                .at(400, 100)
                .view(textureChef)
                .buildAndAttach();

    }

    public void startCooking(String order) throws InterruptedException {
        System.out.println(name + " está preparando: " + order);
        Thread.sleep(5000);
    }

    public void finishCooking(String order) {
        System.out.println(name + " terminó de preparar: " + order);
    }
}
