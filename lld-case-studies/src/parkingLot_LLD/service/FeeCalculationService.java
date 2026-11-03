package service;

import models.Ticket;
import models.fee.FeeType;
import models.fee.FixedFeeCalculation;
import models.fee.HourlyFeeCalculation;

public class FeeCalculationService {
    public double calculateFee(FeeType strategy,Ticket ticket){
        switch(strategy){
            case FIXED : return new FixedFeeCalculation().calculateAmount(ticket);
            default : return new HourlyFeeCalculation().calculateAmount(ticket);
        }
    }
}
