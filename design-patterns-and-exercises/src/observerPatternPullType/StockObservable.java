package observerPatternPullType;

public interface StockObservable {
    void addObserver(StockObserver observer);
    void removeObserver(StockObserver observer);
    void notifyObservers();
    void setStockData(float price, int volume);
}
