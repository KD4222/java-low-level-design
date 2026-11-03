package decoratorPattern;

/**
 * SugarDecorator
 */
public class SugarDecorator extends AddonDecorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    public double getCost() {
        return coffee.getCost() + 1.0; // Adding cost of sugar
    }
}
