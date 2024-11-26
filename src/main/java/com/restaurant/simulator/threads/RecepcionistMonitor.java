package com.restaurant.simulator.threads;

import com.restaurant.simulator.models.Diner;

public class RecepcionistMonitor {
    private int availableTables;
    private final int capacity;

    public RecepcionistMonitor(int capacity) {
        this.capacity = capacity;
        this.availableTables = capacity;
    }

    public synchronized void waitTable(Diner diner) throws InterruptedException {
        while (availableTables == 0) {
            System.out.println("Comensal " + diner.getName() + " está esperando mesa.");
            wait();
        }
        availableTables--;
        System.out.println("Comensal " + diner.getName() + " ha tomado una mesa. Mesas disponibles: " + availableTables);
    }

    public synchronized void leavetable(Diner diner) {
        availableTables++;
        System.out.println("Comensal " + diner.getName() + " liberó una mesa. Mesas disponibles: " + availableTables);
        notifyAll();
    }

    public synchronized int getAvailableTables() {
        return availableTables;
    }
}
