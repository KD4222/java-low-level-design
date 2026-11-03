package observerPatternPullType;

public class GrowStockTracker implements StockObserver {
    private final StockObservable stockMonitor;
    
    public GrowStockTracker(StockAsset stock) {
        this.stockMonitor = stock;
    }

    @Override
    public void update() {
        System.out.println("GrowStockTracker: Stock data updated. Price: " + ((StockAsset) stockMonitor).getPrice() + ", Volume: " + ((StockAsset) stockMonitor).getVolume());
    }

}
