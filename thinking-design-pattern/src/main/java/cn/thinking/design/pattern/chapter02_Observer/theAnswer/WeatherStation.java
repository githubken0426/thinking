package cn.thinking.design.pattern.chapter02_Observer.theAnswer;

public class WeatherStation {
    public static void main(String[] args) {
        Subject subject = new WeatherData();//创建主题
        //建立布告板
        DisplayElement currentDisplay = new CurrentConditionsDisplay(subject);
        DisplayElement statisticDisplay = new StatisticsDisplay(subject);
        subject.setMeasurements("20°", "20", "50hPa");

    }
}
