package abstractFactoryPattern;

public class LuxuryCarFactory implements CarFactory {
    @Override
    public CarInterior createCarInterior() {
        return new LuxuryCarInterior();
    }

    @Override
    public CarExterior createCarExterior() {
        return new LuxuryCarExterior();
    }
}
