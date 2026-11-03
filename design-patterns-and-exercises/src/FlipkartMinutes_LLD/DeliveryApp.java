import services.OrderManager;

import java.util.List;
import java.util.Optional;

import models.*;

public class DeliveryApp {
    OrderManager orderService;

    public DeliveryApp(){
        orderService= new OrderManager("Round-Robin");
    }

    //addCustomer
    public void addCustomer(String name){
        Customer newCustomer= new Customer(name);
        System.out.println("created new Customer: "+ newCustomer.getName());
    }

    //addDeliveryPartner
    public void addDeliveryExecutive(Optional<String> name){
        orderService.getExecutiveService().addAgent(name.get());
        System.out.println("Agent created");
    }

    //addOrder
    public void addOrder(List<Item> items,List<Integer>quantities,Customer customer){
        orderService.addOrder(items,quantities,customer);
    }

    //cancelOrder
    public void cancelOrder(Order order){
        orderService.cancelOrder(order);
    }

    //run active method to run while true to keep assign delivery partners to orders if any exist in the queue
    public void init(){
        while(true){
            if(!orderService.getOrderQueue().waitingOrders.isEmpty()){
                Order order;
				try {
					order = orderService.getOrderQueue().takeNext();
                    orderService.assignDeliveryAgent(order);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
    }

}