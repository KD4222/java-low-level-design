package models;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class OrderQueue {
    public final BlockingQueue<Order> waitingOrders= new LinkedBlockingDeque<>();

    public void add(Order order){
        waitingOrders.add(order);
    }

    public Order takeNext() throws InterruptedException{
        return waitingOrders.take();
    }
}
