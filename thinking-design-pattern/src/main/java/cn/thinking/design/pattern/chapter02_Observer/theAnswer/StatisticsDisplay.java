package cn.thinking.design.pattern.chapter02_Observer.theAnswer;

public class StatisticsDisplay implements DisplayElement, Observer {

    String temperature;
    String humidity;
    String pressure;
    private Subject subject;

    StatisticsDisplay(Subject subject) {
        this.subject = subject;
        this.subject.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("StatisticsDisplay统计avg/max/min," + temperature + "/"
                + temperature + "/" + temperature);
    }

    @Override
    public void update(String temperature, String humidity, String pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

}
