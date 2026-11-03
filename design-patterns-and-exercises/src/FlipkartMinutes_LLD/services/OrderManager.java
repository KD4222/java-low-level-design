package services;

import java.util.HashMap;
import java.util.List;

import models.DeliveryExecutive;
import models.Item;
import models.Order;
import models.OrderQueue;
import models.OrderStatus;
import models.StockInventory;
import repository.OrdersRepo;
import models.driverAssignement.RoundRobinDeliveryAssignment;
import models.Customer;

public class OrderManager {
    OrderQueue orderQueue = new OrderQueue();
    DeliveryExecutiveAssignment executiveService;
    OrdersRepo ordersRepo = new OrdersRepo();
    StockInventory stockInventory = new StockInventory();
    HashMap<Order,DeliveryExecutive> orderToExecutiveMap= new HashMap<>();

    public OrderManager(String strategyType){
        //have to handle the type of strategy passed here which tells us the way in which the driverAssignement will work. 
        @SuppressWarnings("unused")
		DeliveryExecutiveAssignment executiveService= new DeliveryExecutiveAssignment(orderQueue, new RoundRobinDeliveryAssignment());
    }

    //add order
    public synchronized void addOrder(List<Item> items, List<Integer> quantities,Customer customer){
        if(stockInventory.checkInventory(items, quantities)){
            stockInventory.updateInventoryAfterOrderCreated(items, quantities);
            Order newOrder= new Order(items, quantities,customer); 
            orderQueue.add(newOrder);
            ordersRepo.addOrder(newOrder);
            System.out.println("All items in stock and the order is created and added to queue.");
        }
    }

    //cancel order (if picked up, can not be cancelled, otherwise if driver assigned-> free up the driver)
    public synchronized void cancelOrder(Order order){
        if(order.getCurrStatus()==OrderStatus.PICKEDUP){
            System.out.println("Order picked up, can not be cancelled now.");
        }
        else{
            order.setCurrStatus(OrderStatus.CANCELLED);
            if(orderToExecutiveMap.containsKey(order)){
                DeliveryExecutive agent=orderToExecutiveMap.get(order);
                executiveService.freeExecutiveIfAssigned(agent);
            }
        }
    }

    public synchronized void assignDeliveryAgent(Order order){
        DeliveryExecutive agent=executiveService.findAvailableDeliveryExecutive();
        orderToExecutiveMap.put(order,agent);
        order.setCurrStatus(OrderStatus.PICKEDUP);
        System.out.println("Order id:"+order.getOrderId() + "has been picked up by :"+agent.getName());
        return;
    }

	public OrderQueue getOrderQueue() {
		return orderQueue;
	}

	public void setOrderQueue(OrderQueue orderQueue) {
		this.orderQueue = orderQueue;
	}

	public DeliveryExecutiveAssignment getExecutiveService() {
		return executiveService;
	}

	public void setExecutiveService(DeliveryExecutiveAssignment executiveService) {
		this.executiveService = executiveService;
	}

	public OrdersRepo getOrdersRepo() {
		return ordersRepo;
	}

	public void setOrdersRepo(OrdersRepo ordersRepo) {
		this.ordersRepo = ordersRepo;
	}

	public StockInventory getStockInventory() {
		return stockInventory;
	}

	public void setStockInventory(StockInventory stockInventory) {
		this.stockInventory = stockInventory;
	}

	public HashMap<Order, DeliveryExecutive> getOrderToExecutiveMap() {
		return orderToExecutiveMap;
	}

	public void setOrderToExecutiveMap(HashMap<Order, DeliveryExecutive> orderToExecutiveMap) {
		this.orderToExecutiveMap = orderToExecutiveMap;
	}
}