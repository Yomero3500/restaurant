package com.restaurant.simulator.models;

import com.restaurant.simulator.monitors.WaiterMonitor;

public class Chef extends Thread{
    private final WaiterMonitor waiterMonitor;

    public Chef(WaiterMonitor waiterMonitor, String name){
        super(name);
        this.waiterMonitor = waiterMonitor;
    }

    @Override
    public void run() {
        try {
            while(true) {
                String order = waiterMonitor.takeOrder();
                System.out.println(this.getName() + " esta preparando: " + order);

                Thread.sleep((int) (Math.random() * 5000) + 2000);

                waiterMonitor.addReadyOrder(order);
                System.out.println(this.getName() + " ha termiando de preparar: " + order);
            }
        } catch (InterruptedException e) {
            System.out.println(this.getName() + " ha terminado su turno.");
        }
    }
}
