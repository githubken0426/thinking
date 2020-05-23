package cn.thinking.design.pattern.chapter02_Observer.javaAPI;

public class CurrentConditionsDisplay implements Observer, DisplayElement {
    String temperature;
    String humidity;
    String pressure;
    private Observable observable;

    CurrentConditionsDisplay(Observable observable) {
        this.observable = observable;
        this.observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("CurrentConditionsDisplay,温度：" + temperature + ",湿度："
                + humidity + "，气压：" + pressure);
    }

    /**
     * push 使用参数Object，Object封装了参数
     * pull 拉取Observable字段的值
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg != null)
            System.out.println(arg.getClass().getCanonicalName());
        if (o instanceof WeatherData) {
            this.temperature = ((WeatherData) o).getTemperature();
            this.humidity = ((WeatherData) o).getHumidity();
            this.pressure = ((WeatherData) o).getPressure();
            display();
        }
    }

}
