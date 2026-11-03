package parkingLot_LLD;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parkingLot_LLD.ParkingLot.EntranceGate;
import parkingLot_LLD.ParkingLot.ExitGate;
import parkingLot_LLD.ParkingLot.ParkingBuilding;
import parkingLot_LLD.ParkingLot.ParkingLevel;
import parkingLot_LLD.ParkingLot.ParkingLot;
import parkingLot_LLD.ParkingLot.EntranceGate;
import parkingLot_LLD.ParkingLot.ExitGate;
import parkingLot_LLD.ParkingSpotManager.FourWheelerSpotManager;
import parkingLot_LLD.ParkingSpotManager.SpotManager;
import parkingLot_LLD.ParkingSpotManager.TwoWheelerSpotManager;
import parkingLot_LLD.SpotLookupStrategy.ParkingSpotLookupStrategy;
import parkingLot_LLD.SpotLookupStrategy.RandomLookupStrategy;
import parkingLot_LLD.entity.ParkingSpot;
import parkingLot_LLD.enums.VehicleType;
import parkingLot_LLD.pricing.CostComputation;
import parkingLot_LLD.pricing.FixedPricingStrategy;
import parkingLot_LLD.entity.Vehicle;
import parkingLot_LLD.payment.CashPayment;

@SuppressWarnings("unused")
public class ParkingLotClient {
    public static void main(String[] args){
        ParkingSpotLookupStrategy lookupStrategy = new RandomLookupStrategy();

        Map<VehicleType,SpotManager> levelOneManagers = new HashMap<>();

        levelOneManagers.put(VehicleType.TWO_WHEELER, new TwoWheelerSpotManager(List.of(new ParkingSpot("L1-S1"), new ParkingSpot("L1-S2"   )), lookupStrategy));   
        levelOneManagers.put(VehicleType.FOUR_WHEELER,
                new FourWheelerSpotManager(List.of(new ParkingSpot("L1-S3")), lookupStrategy));

        ParkingLevel levelOne = new ParkingLevel(1, levelOneManagers);

        Map<VehicleType,SpotManager> levelTwoManagers = new HashMap<>();

        levelTwoManagers.put(VehicleType.TWO_WHEELER, new TwoWheelerSpotManager(List.of(new ParkingSpot("L2-S1"), new ParkingSpot("L2-S2"   )), lookupStrategy));   
        levelTwoManagers.put(VehicleType.FOUR_WHEELER,
                new FourWheelerSpotManager(List.of(new ParkingSpot("L2-S3")), lookupStrategy));

        ParkingLevel levelTwo = new ParkingLevel(2, levelTwoManagers);
        
        ParkingBuilding parkingBuilding = new ParkingBuilding(List.of(levelOne, levelTwo));

        ParkingLot newParkingLot = new ParkingLot(parkingBuilding,new EntranceGate(), new ExitGate(new CostComputation(new FixedPricingStrategy())));

        Vehicle bike = new Vehicle("BIKE-101", VehicleType.TWO_WHEELER);
        Vehicle car = new Vehicle("CAR-201", VehicleType.FOUR_WHEELER);

        Ticket t1 = newParkingLot.vehicleArrives(bike);
        Ticket t2 = newParkingLot.vehicleArrives(car);

        newParkingLot.vehicleExits(t1, new CashPayment());
        newParkingLot.vehicleExits(t2, new CashPayment());
    }
}
