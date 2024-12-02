package com.restaurant.simulator.app;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.restaurant.simulator.utils.SpriteLoader;
import com.almasb.fxgl.texture.Texture;
import com.restaurant.simulator.models.Chef;
import com.restaurant.simulator.models.Diner;
import com.restaurant.simulator.monitors.RecepcionistMonitor;
import com.restaurant.simulator.models.Waiter;
import com.restaurant.simulator.monitors.WaiterMonitor;
import javafx.geometry.Point2D;

import javafx.geometry.Rectangle2D;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

public class MainApp extends GameApplication {

    private List<Entity> waiterEntities = new ArrayList<>();
    private List<Entity> chefEntities = new ArrayList<>();

    private RecepcionistMonitor recepcionistMonitor;
    private WaiterMonitor waiterMonitor;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Restaurant Simulation");
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setVersion("1.0");
    }

    @Override
    protected void initGame() {
        // Definir posiciones de mesas
        Point2D[] tablePositions = {
                new Point2D(150, 200),
                new Point2D(250, 300),
                new Point2D(350, 200),
                new Point2D(450, 300),
                new Point2D(500, 200)
        };
        // Initialize Monitors
        int capacity = 5;
        recepcionistMonitor = new RecepcionistMonitor(tablePositions.length, tablePositions);
        waiterMonitor = new WaiterMonitor();
        for (int i = 0; i < capacity; i++) {
            Entity mesa = FXGL.entityBuilder()
                    .at()
        }
        // Create Chefs
        int numChefs = (int) Math.ceil(capacity * 0.15);
        for (int i = 0; i < numChefs; i++) {
            String chefName = "Chef" + (i + 1);
            Chef chef = new Chef(waiterMonitor, chefName);
            chef.start();

            Entity chefEntity = FXGL.entityBuilder()
                    .at(100, 400 + i * 50)
                    .viewWithBBox("chef.png")
                    .buildAndAttach();

            chefEntities.add(chefEntity);
        }

        // Create Waiters
        int numWaiters = (int) Math.ceil(capacity * 0.1);
        for (int i = 0; i < numWaiters; i++) {
            String waiterName = "Mesero " + (i + 1);
            Waiter waiter = new Waiter(waiterMonitor, waiterName);
            waiter.start();

            Entity waiterEntity = FXGL.entityBuilder()
                    .at(200, 400 + i * 50)
                    .viewWithBBox("waiter.png")
                    .buildAndAttach();

            waiterEntities.add(waiterEntity);
        }


        // Create Diners and Animate Movement
        for (int i = 1; i <= capacity*3; i++) {
            String dinerName = "C" + i;
            Diner diner = new Diner(recepcionistMonitor, waiterMonitor, dinerName);
            diner.start();
        }
    }

    @Override
    protected void initUI() {
        // Add UI components if needed, e.g., labels to show status
        FXGL.addUINode(FXGL.getUIFactoryService().newText("Restaurant Simulation", 20), 10, 20);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
