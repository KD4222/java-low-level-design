package models.driverAssignement;

import java.util.List;

import models.DeliveryExecutive;
import repository.DeliveryExecutivesRepo;

public class RoundRobinDeliveryAssignment extends DeliveryExecutiveAssignmentStrategy {
    private int nextIndex;

    @Override
    public synchronized DeliveryExecutive findFreeDriver(DeliveryExecutivesRepo agentRepo) {
        List<DeliveryExecutive> agents = agentRepo.getAgents();
        if (agents.isEmpty()) {
            return null;
        }

        int start = nextIndex % agents.size();
        for (int offset = 0; offset < agents.size(); offset++) {
            int index = (start + offset) % agents.size();
            DeliveryExecutive agent = agents.get(index);
            if (Boolean.TRUE.equals(agent.getIsFree())) {
                nextIndex = (index + 1) % agents.size();
                return agent;
            }
        }
        return null;
    }
}
