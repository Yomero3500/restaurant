package com.restaurant.simulator.app;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.restaurant.simulator.models.Diner;
import com.restaurant.simulator.threads.RecepcionistMonitor;

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
        RecepcionistMonitor recepcionistMonitor = new RecepcionistMonitor(5);

        for (int i = 1; i <= 10; i++) {
            Diner diner = new Diner(recepcionistMonitor, "Comensal " + i);
            diner.start();
        }
    }
}
