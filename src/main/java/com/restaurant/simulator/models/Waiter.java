package com.restaurant.simulator.models;

import com.restaurant.simulator.controllers.WaiterController;
import com.restaurant.simulator.monitors.WaiterMonitor;
import javafx.geometry.Point2D;
import java.util.Random;

public class Waiter extends Thread {
    private final WaiterMonitor waiterMonitor;
    private final WaiterController waiterController;
    private Point2D[] tablePositions = {
            new Point2D(130, 200),
            new Point2D(270, 305),
            new Point2D(130, 410),
            new Point2D(350, 200),
            new Point2D(490, 305),
            new Point2D(490, 410),
            new Point2D(420, 160),
            new Point2D(490, 160)
    };

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

                waiterController.moveToKitchen(400, 200);
                waiterMonitor.addOrderChef(order);

                String readyOrder = waiterMonitor.takeReadyOrder();
                System.out.println(this.getName() + " entregó: " + readyOrder);

                System.out.println("La mesa del cliente es:");
                String dinerName = readyOrder.split(" ")[1];
                Random random = new Random();
                int mesaRandom = random.nextInt(7);
                Point2D point = tablePositions[mesaRandom];
                waiterController.moveToTable(point);
                waiterMonitor.deliverFood(dinerName);
                waiterController.deliverOrder(readyOrder);

                Thread.sleep((int) (Math.random() * 2000));
            }
        } catch (InterruptedException e) {
            System.out.println("Mesero " + this.getName() + " ha terminado su turno.");
        }
    }
}
