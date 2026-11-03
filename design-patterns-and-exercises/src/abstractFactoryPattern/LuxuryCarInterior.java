package abstractFactoryPattern;

/**
 * LuxuryCarInterior
 */
public class LuxuryCarInterior implements CarInterior {
    @Override
    public void addComponents() {
        System.out.println("Adding components to Luxury Car Interior");
    }
}
