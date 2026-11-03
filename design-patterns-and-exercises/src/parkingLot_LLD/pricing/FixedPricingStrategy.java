package parkingLot_LLD.pricing;

import parkingLot_LLD.Ticket;

public class FixedPricingStrategy implements PricingStrategy {
    
    @Override 
    public double calculateCost(Ticket ticket) {
        return 100.0; // Example fixed cost
    }
}
