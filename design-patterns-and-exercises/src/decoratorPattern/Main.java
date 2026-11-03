package decoratorPattern;

public class Main {
    public static void main(String[] args) {
        // Create a basic coffee
        Coffee coffee = new BasicCoffee();

        // Decorate the coffee with milk and sugar
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);

        // Display the final description and cost
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());
    }
}