package repository;

import java.util.concurrent.ConcurrentHashMap;

import models.Order;

public class OrdersRepo {
    ConcurrentHashMap<String,Order> ordersMap;

    public OrdersRepo(){
        ordersMap = new ConcurrentHashMap<>();
    }

    public void addOrder(Order order){
        ordersMap.put(order.getOrderId(),order);
        System.out.println("Order added to repo");
    }
    
    public void removeOrder(Order order){
        ordersMap.remove(order.getOrderId());
        System.out.println("Order removed from repo");
    }

    public Order getOrder(String id) throws IllegalAccessException {
        if(ordersMap.containsKey(id)){
            return ordersMap.get(id);
        }
        else throw new IllegalAccessException("Order for this order Id does not exist");
    }
}
