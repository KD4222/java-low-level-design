package parkingLot_LLD.pricing;

import parkingLot_LLD.Ticket;

public class CostComputation {
    private final PricingStrategy pricingStrategy;

    public CostComputation(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public double computeCost(Ticket ticket) {
        return pricingStrategy.calculateCost(ticket);
    }
}
