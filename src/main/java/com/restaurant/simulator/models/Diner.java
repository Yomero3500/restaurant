package com.restaurant.simulator.models;

import com.restaurant.simulator.controllers.CustomerController;
import com.restaurant.simulator.monitors.RecepcionistMonitor;
import com.restaurant.simulator.monitors.WaiterMonitor;

public class Diner extends Thread {
    private RecepcionistMonitor recepcionistMonitor;
    private WaiterMonitor waiterMonitor;
    private CustomerController customerController; // Nuevo controlador
    private boolean hasReceivedFood = false;

    public Diner(RecepcionistMonitor recepcionistMonitor, WaiterMonitor waiterMonitor, String name) {
        super(name);
        this.recepcionistMonitor = recepcionistMonitor;
        this.waiterMonitor = waiterMonitor;
        this.customerController = new CustomerController(name); // Instancia del controlador
    }

    @Override
    public void run() {
        try {
            System.out.println("Comensal " + this.getName() + " ha llegado al restaurante.");

            // Movimiento hacia el restaurante
            Thread.sleep(1000); // Simular el tiempo de entrada
            customerController.moveToTable(200, 300);

            recepcionistMonitor.waitTable(this);

            System.out.println("Comensal " + this.getName() + " está listo para ordenar.");
            waiterMonitor.addOrder("Orden " + this.getName());

            waiterMonitor.waitFood(this.getName());

            System.out.println("Comensal " + this.getName() + " está comiendo.");
            Thread.sleep((int) (Math.random() * 5000) + 2000);

            System.out.println("Comensal " + this.getName() + " ha terminado de comer y está saliendo.");

            customerController.exitRestaurant();

            recepcionistMonitor.leavetable(this);
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
