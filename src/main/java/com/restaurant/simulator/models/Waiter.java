package com.restaurant.simulator.models;

import com.restaurant.simulator.monitors.WaiterMonitor;

public class Waiter extends Thread{
    private final WaiterMonitor waiterMonitor;

    public Waiter(WaiterMonitor waiterMonitor, String name) {
        super(name);
        this.waiterMonitor = waiterMonitor;
    }

    @Override
    public void run(){
        try {
            while(true){
                String order = waiterMonitor.takeOrder();
                System.out.println(this.getName() + " tomó la orden: " + order);

                Thread.sleep((int) (Math.random() * 3000) + 1000);

                String readyOrder = waiterMonitor.takeReadyOrder();
                System.out.println(this.getName() + " entregó: " + readyOrder);

                String dinerName = readyOrder.split(" ")[2];
                for (Thread thread : Thread.getAllStackTraces().keySet()) {
                    if (thread instanceof Diner && thread.getName().equals(dinerName)) {
                        ((Diner) thread).receiveFood();
                        break;
                    }
                }

                Thread.sleep((int) (Math.random() * 2000) + 1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Mesero " + this.getName() + " ha terminado su turno.");
        }
    }
}
