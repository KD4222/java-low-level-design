package models.fee;

import java.time.LocalDateTime;

import models.Ticket;

public class HourlyFeeCalculation implements FeeCalculationStrategy {

    @Override
    public double calculateAmount(Ticket ticket) {
        if(LocalDateTime.now().getDayOfMonth()==ticket.getStartDateTime().getDayOfMonth()){
            double numHours= (double)LocalDateTime.now().getHour()-ticket.getStartDateTime().getHour();
            return numHours;
        }
        return 200.00;
    }
}
