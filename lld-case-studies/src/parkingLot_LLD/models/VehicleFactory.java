package models;

public class VehicleFactory {

    private static final VehicleFactory INSTANCE= new VehicleFactory();
    public VehicleFactory getInstance(){
        return INSTANCE;
    }

    private VehicleFactory(){};

    public Vehicle createVehicle(String VehicleNum, VehicleType type){
        switch(type){
            case CAR: return new Car(VehicleNum);
            default : return new Bike(VehicleNum);
        }
    }
}
