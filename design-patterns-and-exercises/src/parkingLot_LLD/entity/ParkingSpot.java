package parkingLot_LLD.entity;

public class ParkingSpot {
    private String spotId;
    private boolean isFree=true;

    public ParkingSpot(String spotId) {
        this.spotId = spotId;
    }

    public String getSpotId() {
        return this.spotId;
    }

    public boolean isSpotFree() {
        return this.isFree;
    }

    public void releaseSpot() {
        this.isFree = true;
    }

    public void occupySpot() {
        this.isFree = false;
    }
}
