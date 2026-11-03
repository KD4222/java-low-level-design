package parkingLot_LLD.pricing;

import parkingLot_LLD.Ticket;

public interface PricingStrategy {
    double calculateCost(Ticket ticket);
}
