package models;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String orderId;
    private final Customer customer;
    private HashMap<Item,Integer> itemToQuantity;
    private OrderStatus currStatus = OrderStatus.PLACED;

    public Order(List<Item> items,List<Integer> quantities,Customer customer){
        orderId = UUID.randomUUID().toString();
        this.customer=customer;
        this.currStatus=OrderStatus.CREATED;
        itemToQuantity= new HashMap<>();
        for(int i=0;i<items.size();i++){
            itemToQuantity.put(items.get(i),quantities.get(i));
        }
        System.out.println("Order created and added to queue");
    }

	public String getOrderId() {
		return orderId;
	}

	public HashMap<Item, Integer> getItemToQuantity() {
		return itemToQuantity;
	}

	public OrderStatus getCurrStatus() {
		return currStatus;
	}

	public void setCurrStatus(OrderStatus currStatus) {
		this.currStatus = currStatus;
	}

	public Customer getCustomer() {
		return customer;
	}
}
