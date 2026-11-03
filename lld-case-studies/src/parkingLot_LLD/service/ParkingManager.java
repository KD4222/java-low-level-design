package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.EntryGate;
import models.ExitGate;
import models.ParkingLot;
import models.Slot;
import models.Ticket;
import models.Vehicle;
import models.VehicleFactory;
import models.fee.*;

public class ParkingManager {
    private static final ParkingManager INSTANCE = new ParkingManager();
    private ParkingLot parkingLot;
    private final Map<String,Ticket> ticketMap= new HashMap<>();
    private List<ExitGate> exits;
    private List<EntryGate> entryGates;
    private int nextGateNum =1;
    private final FeeCalculationService feeCalculator= new FeeCalculationService();
    private  VehicleFactory vehicleFactory;

    private ParkingManager(){
        parkingLot= parkingLot.getInstance();
        vehicleFactory= vehicleFactory.getInstance();
    };

    public ParkingManager getInstance(){return INSTANCE;}

    public Ticket getTicketForVehicleNum(String vehicleNum){
        return ticketMap.get(vehicleNum);
    }

    public List<ExitGate> getExits() {
        return exits;
    }

    public List<EntryGate> getEntryGates(){
        return entryGates;
    }

    public void unpark(String vehicleNum,FeeType type) {
        Ticket ticket= ticketMap.get(vehicleNum);
        double amount=feeCalculator.calculateFee(type,ticket);
        System.out.println("Please pay amount : "+ amount);
        //payment service -> if success

        //free up slot
        ticket.getSlot().vacate();
        ticketMap.remove(vehicleNum);

        System.out.println("Car unparked.");
    }

    public void park(String vehicleNum, Slot slot) {
        Vehicle vehicle= vehicleFactory.createVehicle(vehicleNum,slot.getSlotType());
        Ticket ticket= new Ticket(vehicle, slot);
        ticketMap.put(vehicleNum, ticket);
        System.out.println(slot.getSlotType().name() + " number : " + vehicleNum +" parked.");
    } 

    //make new entry gate
    public EntryGate addEntryGate(){
        EntryGate entryGate = new EntryGate(nextGateNum++);
        System.err.println("New entry gate created. Gate number : "+entryGate.getGateId());
        return entryGate;
    }

    //make new exit gate
    public ExitGate addExitGate(){
        ExitGate exitGate= new ExitGate(nextGateNum++);
        System.err.println("New exit gate created. Gate number : "+ exitGate.getGateId());
        return exitGate;
    }
}
