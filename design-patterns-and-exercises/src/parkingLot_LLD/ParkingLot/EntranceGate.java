package parkingLot_LLD.ParkingLot;

import parkingLot_LLD.Ticket;
import parkingLot_LLD.entity.Vehicle;

public class EntranceGate {
    public Ticket enterVehicle(ParkingBuilding building, Vehicle vehicle) {
        return building.allocate(vehicle);
    }
}
