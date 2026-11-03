package parkingLot_LLD.SpotLookupStrategy;

import java.util.List;

import parkingLot_LLD.entity.ParkingSpot;

/**
 * ParkingSpotLookupStrategy
 */
public interface ParkingSpotLookupStrategy {
    ParkingSpot findSpot(List<ParkingSpot> spots);
}
