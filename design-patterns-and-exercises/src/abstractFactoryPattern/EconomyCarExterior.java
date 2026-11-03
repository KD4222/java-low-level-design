package abstractFactoryPattern;

/**
 * EconomyCarExterior
 */
public class EconomyCarExterior implements CarExterior {
    @Override
    public void addComponents() {
        System.out.println("Adding components to Economy Car Exterior");
    }
}
