package models.fee;

import models.Ticket;

public class FixedFeeCalculation implements FeeCalculationStrategy {

    @Override
    public double calculateAmount(Ticket ticket) {
        return 100.00;
    }
    
}
