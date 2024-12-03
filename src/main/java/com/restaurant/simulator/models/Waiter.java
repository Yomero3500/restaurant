package com.restaurant.simulator.models;

import com.restaurant.simulator.controllers.WaiterController;
import com.restaurant.simulator.monitors.WaiterMonitor;

public class Waiter extends Thread {
    private final WaiterMonitor waiterMonitor;
    private final WaiterController waiterController;

    public Waiter(WaiterMonitor waiterMonitor, String name) {
        super(name);
        this.waiterMonitor = waiterMonitor;
        this.waiterController = new WaiterController(name); // Nuevo controlador
    }

    @Override
    public void run() {
        try {
            while (true) {
                String order = waiterMonitor.takeOrder();
                System.out.println(this.getName() + " tomó la orden: " + order);

                waiterController.moveToKitchen(400, 150);
                waiterMonitor.addOrderChef(order);

                String readyOrder = waiterMonitor.takeReadyOrder();
                System.out.println(this.getName() + " entregó: " + readyOrder);

                String dinerName = readyOrder.split(" ")[1];
                waiterController.moveToTable(300, 200); // Mover hacia la mesa del cliente
                waiterMonitor.deliverFood(dinerName);
                waiterController.deliverOrder(readyOrder);

                Thread.sleep((int) (Math.random() * 2000));
            }
        } catch (InterruptedException e) {
            System.out.println("Mesero " + this.getName() + " ha terminado su turno.");
        }
    }
}
