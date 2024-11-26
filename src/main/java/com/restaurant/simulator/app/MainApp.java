package com.restaurant.simulator.app;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.restaurant.simulator.models.Diner;
import com.restaurant.simulator.monitors.RecepcionistMonitor;
import com.restaurant.simulator.models.Waiter;
import com.restaurant.simulator.monitors.WaiterMonitor;

public class MainApp extends GameApplication {
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Restaurant Simulation");
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setVersion("1.0");
    }

    public static void main(String[] args) {
        //launch(args);
        int capacity = 5;
        int numWaiters = (int) Math.ceil(capacity * 0.1);
        RecepcionistMonitor recepcionistMonitor = new RecepcionistMonitor(5);
        WaiterMonitor waiterMonitor = new WaiterMonitor();

        for (int i = 0; i <= numWaiters; i++){
            Waiter waiter = new Waiter(waiterMonitor, "Mesero " + i);
            waiter.start();
        }

        for (int i = 1; i <= 10; i++) {
            Diner diner = new Diner(recepcionistMonitor, waiterMonitor, "Comensal " + i);
            diner.start();
        }
    }
}
