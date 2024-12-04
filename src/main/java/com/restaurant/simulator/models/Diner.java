package com.restaurant.simulator.models;

import com.restaurant.simulator.controllers.CustomerController;
import com.restaurant.simulator.monitors.RecepcionistMonitor;
import com.restaurant.simulator.monitors.WaiterMonitor;
import javafx.geometry.Point2D;

public class Diner extends Thread {
    private RecepcionistMonitor recepcionistMonitor;
    private WaiterMonitor waiterMonitor;
    private CustomerController customerController;
    private boolean hasReceivedFood = false;
    private Point2D mesa;

    public Diner(RecepcionistMonitor recepcionistMonitor, WaiterMonitor waiterMonitor, String name) {
        super(name);
        this.recepcionistMonitor = recepcionistMonitor;
        this.waiterMonitor = waiterMonitor;
        this.customerController = new CustomerController(name);
    }

    public CustomerController getCustomerController() {
        return customerController;
    }

    public Point2D getMesa() {
        return mesa;
    }

    @Override
    public void run() {
        try {
            System.out.println("Comensal " + this.getName() + " ha llegado al restaurante.");
            Thread.sleep(200); // Simular el tiempo de entrada
            customerController.moveToLobby(700, 550);
            Thread.sleep(1000);
            mesa = recepcionistMonitor.assignTable();
            customerController.moveToTable(mesa.getX(), mesa.getY());

            System.out.println("Comensal " + this.getName() + " está listo para ordenar.");
            waiterMonitor.addOrder("Orden " + this.getName());

            waiterMonitor.waitFood(this.getName());

            System.out.println("Comensal " + this.getName() + " está comiendo.");
            Thread.sleep((int) (Math.random() * 5000) + 5000);
            System.out.println("Comensal " + this.getName() + " ha terminado de comer y está saliendo.");

            customerController.exitRestaurant();

            recepcionistMonitor.releaseTable(mesa);
        } catch (InterruptedException e) {
            System.err.println("Comensal " + this.getName() + " fue interrumpido: " + e.getMessage());
        }
    }

    public synchronized void receiveFood() {
        System.out.println("Valor cambiado");
        hasReceivedFood = true;
        notifyAll();
    }
}
