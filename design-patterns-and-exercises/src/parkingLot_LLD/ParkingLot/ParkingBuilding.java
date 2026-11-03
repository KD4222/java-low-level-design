package parkingLot_LLD.ParkingLot;

import java.util.List;

import parkingLot_LLD.Ticket;
import parkingLot_LLD.entity.ParkingSpot;
import parkingLot_LLD.entity.Vehicle;

public class ParkingBuilding {
    List<ParkingLevel> levels;

    public ParkingBuilding(List<ParkingLevel> levels) {
        this.levels = levels;
    }

    public List<ParkingLevel> getLevels() {
        return levels;
    }

    Ticket allocate(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
            if (level.hasAvailability(vehicle.getVehicleType())) {
                ParkingSpot spot = level.park(vehicle.getVehicleType());
                if (spot != null) {
                    Ticket ticket = new Ticket(vehicle, level, spot);
                    System.out.println("Parking allocated at level : " + level.getLevelNumber() + ", Spot ID: " + spot.getSpotId());
                    return ticket;
                }
            }
        }
        throw new RuntimeException("PARKING FULL for vehicle type: " + vehicle.getVehicleType());
    }

    void release(Ticket ticket) {
        ticket.getLevel().unpark(ticket.getVehicle().getVehicleType(), ticket.getSpot());
    }
}
