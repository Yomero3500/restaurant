package com.restaurant.simulator.controllers;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import com.restaurant.simulator.utils.SpriteLoader;
import javafx.geometry.Point2D;

public class RecepcionistController {
    private Entity recepcionistEntity;

    public RecepcionistController(){
        Texture textura = SpriteLoader.getSprite("pajaro.png",1,1,32,32);
        recepcionistEntity = FXGL.entityBuilder()
                .at(600, 260)
                .scale(2,2)
                .view(textura)
                .buildAndAttach();
    }
}
