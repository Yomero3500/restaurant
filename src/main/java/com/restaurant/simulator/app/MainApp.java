package com.restaurant.simulator.app;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.restaurant.simulator.controllers.TableController;
import com.restaurant.simulator.models.Chef;
import com.restaurant.simulator.models.Diner;
import com.restaurant.simulator.monitors.RecepcionistMonitor;
import com.restaurant.simulator.models.Waiter;
import com.restaurant.simulator.monitors.WaiterMonitor;
import javafx.geometry.Point2D;


public class MainApp extends GameApplication {


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
        FXGL.getGameScene().setBackgroundRepeat("fondo.png");
        // Definir posiciones de mesas
        Point2D[] tablePositions = {
                new Point2D(150, 200),
                new Point2D(150, 300),
                new Point2D(250, 200),
                new Point2D(250, 300),
                new Point2D(350, 200),
                new Point2D(350, 300),
                new Point2D(450, 200),
                new Point2D(450, 300)
        };
        // Initialize Monitors
        int capacity = 8;
        recepcionistMonitor = new RecepcionistMonitor(tablePositions.length, tablePositions);
        waiterMonitor = new WaiterMonitor();
        for (int i = 0; i < capacity; i++) {
            Point2D position = tablePositions[i];
            TableController mesa = new TableController(position);
        }
        // Create Chefs
        int numChefs = (int) Math.ceil(capacity * 0.15);
        for (int i = 0; i < numChefs; i++) {
            String chefName = "Chef" + (i + 1);
            Chef chef = new Chef(waiterMonitor, chefName);
            chef.start();
        }

        // Create Waiters
        int numWaiters = (int) Math.ceil(capacity * 0.1);
        for (int i = 0; i < numWaiters; i++) {
            String waiterName = "Mesero " + (i + 1);
            Waiter waiter = new Waiter(waiterMonitor, waiterName);
            waiter.start();
        }


        // Create Diners
        for (int i = 1; i <= capacity*3; i++) {
            String dinerName = "C" + i;
            Diner diner = new Diner(recepcionistMonitor, waiterMonitor, dinerName);
            diner.start();
        }
    }

    @Override
    protected void initUI() {
        FXGL.addUINode(FXGL.getUIFactoryService().newText("Restaurant Simulation", 20), 10, 20);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
