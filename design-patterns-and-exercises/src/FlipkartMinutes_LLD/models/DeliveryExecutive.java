package models;

import java.util.UUID;

public class DeliveryExecutive {
    String name;
    Order currentOrder = null;
    Boolean isFree=true;

    //A names delivery executive could be there
    public DeliveryExecutive(String name){
        this.name=name;
    }

    // simply a new deliveryExecutive can be instantiatied
    public DeliveryExecutive(){
        this.name=UUID.randomUUID().toString();
    }

    public void assignOrder(Order order){
        if(isFree){
            currentOrder=order;
            isFree=false;
        }else{
            System.out.println("Delivery executive already busy, please find another.");
        }
    }

    public void orderComplete(){
        this.isFree=true;
        this.currentOrder=null;
        System.out.println("Order completed,executive now free");
    }

	public String getName() {
		return name;
	}

	public Order getCurrentOrder() {
		return currentOrder;
	}

	public Boolean getIsFree() {
		return isFree;
    }

	public void setCurrentOrder(Order currentOrder) {
		this.currentOrder = currentOrder;
	}

	public void freeUpAgent() {
		this.isFree = false;
        this.currentOrder=null;
	}
}
