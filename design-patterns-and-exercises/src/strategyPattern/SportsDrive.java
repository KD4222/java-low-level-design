package strategyPattern;
public class SportsDrive implements DriveStrategy {

    @Override
    public void drive() {
        System.out.println("Sports drive capablity");
    }
}
