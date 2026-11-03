package observerPattern;

public class CurrentWeatherDisplay implements WeatherObserver {
    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    public void display() {
        System.out.println("Current Weather: " + temperature + "°C, " + humidity + "% humidity, " + pressure + " hPa");
    }
}
