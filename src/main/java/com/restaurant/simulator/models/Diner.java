package com.restaurant.simulator.models;

import com.restaurant.simulator.threads.RecepcionistMonitor;

public class Diner extends Thread {
    private RecepcionistMonitor recepcionistMonitor;

    public Diner(RecepcionistMonitor recepcionistMonitor, String name) {
        super(name);
        this.recepcionistMonitor = recepcionistMonitor;
    }

    @Override
    public void run() {
        try {
            System.out.println("Comensal " + this.getName() + " ha llegado al restaurante.");

            recepcionistMonitor.waitTable(this);

            System.out.println("Comensal " + this.getName() + " está comiendo.");
            Thread.sleep((int) (Math.random() * 5000) + 2000);

            System.out.println("Comensal " + this.getName() + " ha terminado de comer y está saliendo.");
            recepcionistMonitor.leavetable(this);
        } catch (InterruptedException e) {
            System.err.println("Comensal " + this.getName() + " fue interrumpido: " + e.getMessage());
        }
    }
}