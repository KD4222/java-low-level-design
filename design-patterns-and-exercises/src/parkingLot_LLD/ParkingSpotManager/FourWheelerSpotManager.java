package parkingLot_LLD.ParkingSpotManager;

import java.util.List;
import parkingLot_LLD.SpotLookupStrategy.ParkingSpotLookupStrategy;
import parkingLot_LLD.entity.ParkingSpot;

public class FourWheelerSpotManager extends SpotManager {
    public FourWheelerSpotManager(List<ParkingSpot> spots, ParkingSpotLookupStrategy lookupStrategy) {
        super(spots, lookupStrategy);
    }
}
