package decoratorPattern;

/**
 * MilkDecorator
 */
public class MilkDecorator extends AddonDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    public double getCost() {
        return coffee.getCost() + 2.0; // Adding cost of milk
    }
}
