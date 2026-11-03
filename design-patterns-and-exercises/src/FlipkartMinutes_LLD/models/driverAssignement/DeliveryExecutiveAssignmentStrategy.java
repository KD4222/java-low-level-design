package models.driverAssignement;

import models.DeliveryExecutive;
import repository.DeliveryExecutivesRepo;

public abstract class DeliveryExecutiveAssignmentStrategy {
    public abstract DeliveryExecutive findFreeDriver(DeliveryExecutivesRepo agentRepo);
}
