package strategyPattern;
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Vehicle myCar = new Vehicle(new SportsDrive());

        myCar.driveStrategy.drive();
    }
}
