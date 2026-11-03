package parkingLot_LLD.ParkingSpotManager;

import java.util.List;
import parkingLot_LLD.SpotLookupStrategy.ParkingSpotLookupStrategy;
import parkingLot_LLD.entity.ParkingSpot;

public class TwoWheelerSpotManager extends SpotManager {
    public TwoWheelerSpotManager(List<ParkingSpot> spots, ParkingSpotLookupStrategy lookupStrategy) {
        super(spots, lookupStrategy);
    }     
}
