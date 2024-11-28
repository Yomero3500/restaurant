package com.restaurant.simulator.models;

import com.restaurant.simulator.controllers.CocineroController;
import com.restaurant.simulator.monitors.WaiterMonitor;

public class Chef extends Thread {
    private final WaiterMonitor waiterMonitor;
    private final CocineroController cocineroController;

    public Chef(WaiterMonitor waiterMonitor, String name) {
        super(name);
        this.waiterMonitor = waiterMonitor;
        this.cocineroController = new CocineroController(name); // Nuevo controlador
    }

    @Override
    public void run() {
        try {
            while (true) {
                String order = waiterMonitor.takeOrderChef();
                cocineroController.startCooking(order);

                Thread.sleep((int) (Math.random() * 5000) + 2000);

                waiterMonitor.addReadyOrder(order);
                cocineroController.finishCooking(order);
            }
        } catch (InterruptedException e) {
            System.out.println("Cocinero " + this.getName() + " ha terminado su turno.");
        }
    }
}
