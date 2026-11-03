package abstractFactoryPattern;

public class Main {
    public static void main(String[] args) {
        CarFactory luxuryCarFactory = new LuxuryCarFactory();
        CarInterior luxuryCarInterior = luxuryCarFactory.createCarInterior();
        CarExterior luxuryCarExterior = luxuryCarFactory.createCarExterior();

        luxuryCarInterior.addComponents();
        luxuryCarExterior.addComponents();

        CarFactory economyCarFactory = new EconomyCarFactory();
        CarInterior economyCarInterior = economyCarFactory.createCarInterior();
        CarExterior economyCarExterior = economyCarFactory.createCarExterior();

        economyCarInterior.addComponents();
        economyCarExterior.addComponents();
    }
}
