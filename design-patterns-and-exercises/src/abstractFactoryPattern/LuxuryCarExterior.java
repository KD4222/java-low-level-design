package abstractFactoryPattern;

/**
 * LuxuryCarExterior
 */
public class LuxuryCarExterior implements CarExterior {
    @Override
    public void addComponents() {
        System.out.println("Adding components to Luxury Car Exterior");
    }
}
