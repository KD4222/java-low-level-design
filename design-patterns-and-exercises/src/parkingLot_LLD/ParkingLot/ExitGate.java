package parkingLot_LLD.ParkingLot;

import parkingLot_LLD.Ticket;
import parkingLot_LLD.pricing.CostComputation;
import parkingLot_LLD.payment.Payment;

public class ExitGate {
    private final CostComputation costComputation;

    public ExitGate(CostComputation costComputation) {
        this.costComputation = costComputation;
    }

    public void completeExit(ParkingBuilding building, Ticket ticket,Payment payment) {
        double amount=calculatePrice(ticket);
        boolean success=payment.pay(amount);
        if (!success) {
            throw new RuntimeException("Payment failed. Cannot exit.");
        }
        building.release(ticket);
        System.out.println("Exit completed for vehicle: " + ticket.getVehicle().getVehicleNumber() + ". Amount paid: " + amount);
    }

    private double calculatePrice(Ticket ticket) {
        return costComputation.computeCost(ticket);
    }
}
