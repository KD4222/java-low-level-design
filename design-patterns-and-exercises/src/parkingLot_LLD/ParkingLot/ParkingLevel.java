package parkingLot_LLD.ParkingLot;

import parkingLot_LLD.ParkingSpotManager.SpotManager;
import parkingLot_LLD.entity.ParkingSpot;
import parkingLot_LLD.enums.VehicleType;
import java.util.Map;

public class ParkingLevel {
    private final int levelNumber;
    private final Map<VehicleType, SpotManager> managers;

    public ParkingLevel(int levelNumber, Map<VehicleType, SpotManager> managers) {
        this.levelNumber = levelNumber;
        this.managers = managers;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public boolean hasAvailability(VehicleType vehicleType) {
        SpotManager manager = managers.get(vehicleType);
        return manager!=null && manager.hasFreeSpot();
    }

    public ParkingSpot park(VehicleType type) {
        SpotManager manager = managers.get(type);
        if(manager==null) {
            throw new IllegalArgumentException("No SpotManager for vehicle type: " + type);
        }
        return manager.park();
    }

    public void unpark(VehicleType type, ParkingSpot spot) {
        SpotManager manager = managers.get(type);
        if(manager!=null) {
            manager.unPark(spot);
        }
    }
}
