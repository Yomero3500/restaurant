package com.example.demo;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.EntityFactory;


public class HelloApplication extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1000);
        settings.setHeight(700);
        settings.setTitle("Mi Primer Juego FXGL");
        settings.setVersion("0.1");
        settings.setAppIcon("icon.png");
    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new MyFactory());

        FXGL.spawn("player", 400, 300);
    }

    // Define el EntityFactory
    public static class MyFactory implements EntityFactory {
        @Spawns("player")
        public Entity newPlayer(SpawnData data) {
            return FXGL.entityBuilder(data)
                    .at(data.getX(), data.getY())
                  //  .view("src/main/resources/assets/player.png")
                    .with(new PlayerComponent())
                    .build();
        }
    }

    // Componente personalizado
    public static class PlayerComponent extends Component {
        @Override
        public void onUpdate(double tpf) {

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
