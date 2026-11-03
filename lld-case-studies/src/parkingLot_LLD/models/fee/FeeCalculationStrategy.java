package models.fee;

import models.Ticket;

public interface FeeCalculationStrategy {
    public abstract double calculateAmount(Ticket ticket);
}
