package observerPattern;

public class Main {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        CurrentWeatherDisplay currentWeatherDisplay = new CurrentWeatherDisplay();

        weatherStation.addObserver(currentWeatherDisplay);

        ForecastDisplay forecastWeatherDisplay = new ForecastDisplay();
        
        weatherStation.addObserver(forecastWeatherDisplay);

        weatherStation.setWeatherData(25.0f, 60.0f, 1013.0f);
        weatherStation.setWeatherData(30.0f, 55.0f, 1012.0f);
    }
}
