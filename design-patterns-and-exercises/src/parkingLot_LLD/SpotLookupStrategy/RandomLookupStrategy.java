package parkingLot_LLD.SpotLookupStrategy;

import java.util.List;

import parkingLot_LLD.entity.ParkingSpot;

public class RandomLookupStrategy implements ParkingSpotLookupStrategy {

    @Override
    public ParkingSpot findSpot(List<ParkingSpot> spots) {
        for(ParkingSpot spot : spots) {
            if (spot.isSpotFree()) {
                return spot;
            }
        }
        return null;
    }
}
