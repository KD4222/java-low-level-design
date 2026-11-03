package parkingLot_LLD.ParkingSpotManager;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import parkingLot_LLD.SpotLookupStrategy.ParkingSpotLookupStrategy;
import parkingLot_LLD.entity.ParkingSpot;

public abstract class SpotManager {
    protected final List<ParkingSpot> spots;

    protected final ParkingSpotLookupStrategy strategy;

    private final ReentrantLock lock=new ReentrantLock();

    protected SpotManager(List<ParkingSpot> spots, ParkingSpotLookupStrategy strategy) {
        this.spots = spots;
        this.strategy = strategy;
    }

    public ParkingSpot park() {
        lock.lock();
        try {
            ParkingSpot spot = strategy.findSpot(spots);
            if (spot == null) {
                return null;
            }
            spot.occupySpot();
            return spot;
        } finally {
            lock.unlock();
        }
    }

    public void unPark(ParkingSpot parkingSpot) {
        lock.lock();
        try {
            parkingSpot.releaseSpot();
        } finally {
            lock.unlock();
        }
    }

    public boolean hasFreeSpot() {
        lock.lock();
        try {
            return spots.stream().anyMatch(ParkingSpot::isSpotFree);
        } finally {
            lock.unlock();
        }
    }
}
