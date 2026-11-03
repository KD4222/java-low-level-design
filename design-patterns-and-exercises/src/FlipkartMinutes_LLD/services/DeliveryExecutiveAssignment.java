package services;

import models.DeliveryExecutive;
import models.OrderQueue;
import models.driverAssignement.DeliveryExecutiveAssignmentStrategy;
import repository.DeliveryExecutivesRepo;

public class DeliveryExecutiveAssignment {
    DeliveryExecutivesRepo agentRepo= new DeliveryExecutivesRepo();
    OrderQueue orderQueue;
    DeliveryExecutiveAssignmentStrategy driverStrategy;
    
    public DeliveryExecutiveAssignment(OrderQueue queueToFollow, DeliveryExecutiveAssignmentStrategy strategy){
        //Service instance that returns the free delivery executive based on the required strategy

        this.orderQueue=queueToFollow;
        this.driverStrategy=strategy;
    }

    //add agent

    public void addAgent(String name){
        DeliveryExecutive agent=agentRepo.addAgent(name);
        System.out.println("New agent : " + agent.getName() + "is created and added to the repo.");
    }

    public void addAgent(){
        DeliveryExecutive agent=agentRepo.addAgent();
        System.out.println("New agent : " + agent.getName() + "is created and added to the repo.");
    }

    //assign Order
    public DeliveryExecutive findAvailableDeliveryExecutive(){
        DeliveryExecutive agent;
        agent=driverStrategy.findFreeDriver(agentRepo);
        return agent;
    }

    //order cancelled, free up driver
    public void freeExecutiveIfAssigned(DeliveryExecutive agent){
        if(agent==null){
            System.out.println("No executive to free up for cancelled order as it was not assigned.");
        }
        agent.freeUpAgent();
    }


    
}
