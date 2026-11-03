package models;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private final List<Slot> slots = new ArrayList<>();
    private final int floorNum;
    private int nextSlotNum = 1;

    public Floor(int floorNum){
        this.floorNum=floorNum;
    }

    //addSlot
    public void addSlots(VehicleType type, int numOfSlots){
        for(int i=0;i<numOfSlots;i++){
            Slot newSlot= addSlot(type);
            slots.add(newSlot);
            System.out.println("Slot created with id : "+ newSlot.getId() + "on floor : "+ floorNum);
        }
    }

    private Slot addSlot(VehicleType type){
        return new Slot(this,type,nextSlotNum++);
    }


    public List<Slot> getAvailableSlotsForVehicleType(VehicleType slotType){
        return slots.stream().filter(Slot::isAvailable).toList();
    }
    
    //return availalble slots
    public int getNumberOfSpotsAvailableForVehicleType(VehicleType slotType){
        return getAvailableSlotsForVehicleType(slotType).size();
    }
}
