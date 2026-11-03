package observerPatternPullType;

public class Main {
    public static void main(String[] args) {
        StockAsset stock = new StockAsset();
        GrowStockTracker tracker = new GrowStockTracker(stock);
        stock.addObserver(tracker);
        stock.setStockData(100.0f, 1000);
        stock.setStockData(10.0f, 500);
    }
}
