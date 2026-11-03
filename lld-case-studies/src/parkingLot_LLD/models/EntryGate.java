package models;

import java.util.List;

import service.ParkingManager;

public class EntryGate {
    private final String gateId;
    public String getGateId() {
        return gateId;
    }

    private  ParkingManager parkingManager;
    private  ParkingLot parkingLot;
    
    public EntryGate(int id){
        this.gateId=((Integer)id).toString();
        parkingManager=parkingManager.getInstance();
        parkingLot=parkingLot.getInstance();
    }

    public void enter(String vehicleNum,VehicleType type){
        List<Slot> slots = parkingLot.getAvailableSlotsByType(type);
        if(!slots.isEmpty()){
            System.out.println("New "+type.name()+ " with plate number : " + vehicleNum + " arrived at gate : "+gateId);
            parkingManager.park(vehicleNum,slots.getFirst());
        }
        System.out.println("No slots available");
    }
}
