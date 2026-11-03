package models;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private static final ParkingLot INSTANCE = new ParkingLot();
    private final List<Floor> floors = new ArrayList<>();
    private int nextFloorNum;

    private ParkingLot(){};

    public ParkingLot getInstance(){return INSTANCE;}
    
    //addFloor
    public Floor addFloor(){
        Floor floor= new Floor(nextFloorNum);
        floors.add(floor);
        return floor;
    }

    public int getNumberOfFloors(){
        return floors.size();
    }

    public List<Slot> getAvailableSlotsByType(VehicleType slotType){
        List<Slot> slots= new ArrayList<>();
        for(Floor floor:floors){
            slots.addAll(floor.getAvailableSlotsForVehicleType(slotType));
        }
        System.out.println("Avaialable slots for "+ slotType.name() + " : "+slots);
        return slots;
    }
}
