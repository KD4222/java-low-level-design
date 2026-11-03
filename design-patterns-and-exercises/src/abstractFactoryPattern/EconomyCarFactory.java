package abstractFactoryPattern;

public class EconomyCarFactory implements CarFactory {
    @Override
    public CarInterior createCarInterior() {
        return new EconomyCarInterior();
    }

    @Override
    public CarExterior createCarExterior() {
        return new EconomyCarExterior();
    }
}