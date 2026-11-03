package models;
import models.fee.FeeType;
import service.ParkingManager;

public class ExitGate {
    private final String gateId;
    public String getGateId() {
        return gateId;
    }

    private ParkingManager parkingManager;
    private ParkingLot parkingLot;

    public ExitGate(int id){
        parkingLot=parkingLot.getInstance();
        parkingManager= parkingManager.getInstance();
        this.gateId = ((Integer)id).toString();
    }

    public void exit(String vehicleNum,FeeType feeCalculationType){
        Ticket ticket = parkingManager.getTicketForVehicleNum(vehicleNum);
        if(ticket!=null){
            System.out.println(ticket.getSlot().getSlotType().name()+" with vehicle num : "+vehicleNum + " arrived at exit gate num: "+gateId);
            parkingManager.unpark(vehicleNum,feeCalculationType);
        }
        System.out.println("Ticket does not exists, pay 500.00 fine.");
    }
}
