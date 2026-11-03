package strategyPattern;
public class Vehicle{
        DriveStrategy driveStrategy;
        
        public Vehicle(DriveStrategy driveStrategy){
            this.driveStrategy=driveStrategy;
        }

        public void drive(){
            System.out.println("\n" + this.getClass().getSimpleName() + ": ");
            driveStrategy.drive();
        }
    }