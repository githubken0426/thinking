package cn.thinking.design.pattern.chapter02_Observer.javaAPI;

public class StatisticsDisplay implements Observer, DisplayElement {
    String temperature;
    String humidity;
    String pressure;
    private Observable observable;

    StatisticsDisplay(Observable observable) {
        this.observable = observable;
        this.observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("StatisticsDisplay,avg/max/min" + temperature + "/"
                + temperature + "/" + temperature);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg);
        if (o instanceof WeatherData) {
            this.temperature = ((WeatherData) o).getTemperature();
            this.humidity = ((WeatherData) o).getHumidity();
            this.pressure = ((WeatherData) o).getPressure();
            display();
        }
    }

}
