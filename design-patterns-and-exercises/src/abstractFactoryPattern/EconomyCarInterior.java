package abstractFactoryPattern;

/**
 * EconomyCarInterior
 */
public class EconomyCarInterior implements CarInterior {
    @Override
    public void addComponents() {
        System.out.println("Adding components to Economy Car Interior");
    }
}
