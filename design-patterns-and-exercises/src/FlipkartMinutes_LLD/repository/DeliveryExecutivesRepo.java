package repository;

import java.util.List;
import java.util.ArrayList;
import models.DeliveryExecutive;

public class DeliveryExecutivesRepo {
    private final List<DeliveryExecutive> agents = new ArrayList<>();

    public DeliveryExecutive addAgent(String name){
        DeliveryExecutive agent = new DeliveryExecutive(name);
        agents.add(agent);
        return agent;
    }

    public DeliveryExecutive addAgent(){
        DeliveryExecutive agent = new DeliveryExecutive();
        agents.add(agent);
        return agent;
    }

    public List<DeliveryExecutive> getAgents() {
        return agents;
    }

}
