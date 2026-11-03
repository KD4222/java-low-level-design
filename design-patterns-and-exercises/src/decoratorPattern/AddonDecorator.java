package decoratorPattern;

public abstract class AddonDecorator implements Coffee {

    Coffee coffee;
    public AddonDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
}
