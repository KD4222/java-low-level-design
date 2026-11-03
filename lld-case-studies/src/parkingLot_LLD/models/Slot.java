package models;

public class Slot {
    private final String id;
    private final VehicleType slotType;
    private final Floor floor;
    private boolean available;

    public Slot(Floor floor,VehicleType slotType,int id){
        this.floor=floor;
        this.slotType=slotType;
        this.id="F"+ floor + slotType.name() + id;
        available=true;
    }

    public String getId() {
        return id;
    }

    public VehicleType getSlotType() {
        return slotType;
    }

    public Floor getFloor() {
        return floor;
    }

    public boolean isAvailable() {
        return available;
    }

    public void book(){
        available=false;
        System.out.println("Slot id : " + id + "is booked.");
    }

    public void vacate(){
        available=true;
        System.out.println("Slot id : " + id + "is now vacated.");
    }
}
