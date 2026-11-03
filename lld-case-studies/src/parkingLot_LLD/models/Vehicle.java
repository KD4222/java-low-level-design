package models;

public abstract class Vehicle {
    private final VehicleType type;
    private final String vehicleNum; //unuique identifier

    protected Vehicle(VehicleType type,String vehicleNum){
        this.type=type;
        this.vehicleNum=vehicleNum;
    }

    public VehicleType getType() {
        return type;
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

} 