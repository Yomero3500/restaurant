package com.restaurant.simulator.monitors;

import java.util.LinkedList;
import java.util.Queue;

public class WaiterMonitor {
    private final Queue<String> orderBuffer = new LinkedList<>();
    private final Queue<String> orderBufferChef = new LinkedList<>();
    private final Queue<String> readyBuffer = new LinkedList<>();

    public synchronized void addOrder(String order) {
        if (order == null || order.isEmpty()) {
            throw new IllegalArgumentException("La orden no puede ser nula o vacía");
        }
        orderBuffer.add(order);
        System.out.println("Orden añadida al buffer: " + order);
        notifyAll();
    }

    public synchronized String takeOrder() throws InterruptedException {
        while (orderBuffer.isEmpty()) {
            wait();
        }
        String order = orderBuffer.poll();
        notifyAll();
        return order;
    }

    public synchronized void addOrderChef(String order){
        if (order == null || order.isEmpty()) {
            throw new IllegalArgumentException("La orden no puede ser nula o vacía");
        }
        orderBufferChef.add(order);
        System.out.println("Orden añadida al buffer de chefs: " + order);
        notifyAll();
    }

    public synchronized String takeOrderChef() throws InterruptedException{
        while (orderBufferChef.isEmpty()){
            wait();
        }
        String orderChef = orderBufferChef.poll();
        notifyAll();
        return orderChef;
    }

    public synchronized void addReadyOrder(String readyOrder) {
        if (readyOrder == null || readyOrder.isEmpty()) {
            throw new IllegalArgumentException("La orden lista no puede ser nula o vacía");
        }
        readyBuffer.add(readyOrder);
        System.out.println("Comida lista añadida al buffer: " + readyOrder);
        notifyAll();
    }

    public synchronized String takeReadyOrder() throws InterruptedException {
        while (readyBuffer.isEmpty()) {
            wait();
        }
        String readyOrder = readyBuffer.poll();
        System.out.println("Comida lista tomada del buffer: " + readyOrder);
        notifyAll();
        return readyOrder;
    }
}