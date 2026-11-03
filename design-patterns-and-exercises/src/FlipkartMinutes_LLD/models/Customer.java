package models;

import java.util.List;

public class Customer{
    String name;
    List<Order> orders;
    String address;

    public Customer(String name){
        this.name=name;
    }


	public String getName() {
		return name;
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	public void addOrders(List<Order> orders) {
		this.orders = orders;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}