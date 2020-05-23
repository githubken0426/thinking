package cn.thinking.design.pattern.chapter02_Observer.javaAPI;

public class WeatherStation {

    public static void main(String[] args) {
        WeatherData observable = new WeatherData();
        DisplayElement currentDisplay = new CurrentConditionsDisplay(observable);
        DisplayElement statisticDisplay = new StatisticsDisplay(observable);

        observable.setMeasurements("20°", "20", "50hPa");
    }
}
