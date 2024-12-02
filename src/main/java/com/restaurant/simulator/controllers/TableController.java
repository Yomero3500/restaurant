package com.restaurant.simulator.controllers;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import com.restaurant.simulator.utils.SpriteLoader;
import javafx.geometry.Point2D;

public class TableController {
    private Entity tableEntity;

    public TableController(Point2D position) {

        Texture tableTexture = SpriteLoader.getSprite("interior.png",3,8,32,128);

        tableEntity = FXGL.entityBuilder()
                .at(position)
                .viewWithBBox(tableTexture)
                .buildAndAttach();
    }
}
