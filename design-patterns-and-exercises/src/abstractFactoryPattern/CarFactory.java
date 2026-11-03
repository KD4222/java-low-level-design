package abstractFactoryPattern;

public interface CarFactory {
    CarInterior createCarInterior();
    CarExterior createCarExterior();

    default void produceCompleteCar() {
        CarInterior interior = createCarInterior();
        CarExterior exterior = createCarExterior();
        
        interior.addComponents();
        exterior.addComponents();
    }
}
