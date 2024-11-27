package com.restaurant.simulator.monitors;

import java.util.LinkedList;
import java.util.Queue;

public class WaiterMonitor {
    private final Queue<String> orderBuffer = new LinkedList<>();
    private final Queue<String> readyBuffer = new LinkedList<>();

    public synchronized void addOrder(String order) {
        orderBuffer.add(order);
        System.out.println("Orden añadida al buffer "+ order);
        notifyAll();
    }

    public synchronized String takeOrder() throws InterruptedException {
        while(orderBuffer.isEmpty()){
            wait();
        }
        String order = orderBuffer.poll();
        notifyAll();
        return orderBuffer.poll();
    }

    public synchronized void addReadyOrder(String readyOrder) {
        readyBuffer.add(readyOrder);
        System.out.println("Comida lista añadida al buffer "+readyOrder);
        notifyAll();
    }

    public synchronized String takeReadyOrder() throws InterruptedException {
        while(readyBuffer.isEmpty()) {
            wait();
        }
        String readyOrder = orderBuffer.poll();
        System.out.println("Comida lista tomada del buffer "+readyOrder);
        notifyAll();
        return readyBuffer.poll();
    }
}