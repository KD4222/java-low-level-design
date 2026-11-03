package models;

import java.time.LocalDateTime;

public class Ticket {
    private final Vehicle vehicle;
    private final Slot slot;
    private final LocalDateTime startDateTime;

    public Ticket(Vehicle vehicle, Slot slot){
        this.vehicle=vehicle;
        this.slot=slot;
        startDateTime=LocalDateTime.now();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Slot getSlot() {
        return slot;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }
}   
