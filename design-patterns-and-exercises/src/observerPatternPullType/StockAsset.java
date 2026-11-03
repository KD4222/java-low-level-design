package observerPatternPullType;

import java.util.ArrayList;
import java.util.List;


public class StockAsset implements StockObservable {
    private List<StockObserver> observers;
    private float price;
    private int volume;
    
    public StockAsset() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(StockObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(StockObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (StockObserver observer : observers) {
            observer.update();
        }
    }

    @Override
    public void setStockData(float price, int volume) {
        this.price = price;
        this.volume = volume;
        notifyObservers();
    }

    public List<StockObserver> getObservers() {
        return observers;
    }

    public void setObservers(List<StockObserver> observers) {
        this.observers = observers;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
    
}
