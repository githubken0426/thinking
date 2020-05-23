package cn.thinking.design.pattern.chapter02_Observer.javaAPI;

/**
 * Java内置API实现观察者模式
 *
 * @author ken
 * 2017-6-13 下午02:57:13
 */
public class WeatherData extends Observable {
    String temperature;
    String humidity;
    String pressure;

    public WeatherData() {
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setMeasurements(String temperature, String humidity, String pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        setChanged();
        /**
         * pull(拉)拉模型 主题对象在通知观察者的时候，只传递少量信息。
         * 如果观察者需要更具体的信息，由观察者主动到主题对象中获取，相当于是观察者从主题对象中拉数据。
         * 一般这种模型的实现中，会把主题对象自身通过update()方法传递给观察者，这样在观察者需要获取数据的时候，就可以通过这个引用来获取了。
         *
         * 此处并没有调用notifyObservers()传递数据对象 这表示我们使用的是pull(拉)
         */
        notifyObservers();
        /**
         * push(推)推模型  主题对象向观察者推送主题的详细信息，不管观察者是否需要，推送的信息通常是主题对象的全部或部分数据。
         */
        //notifyObservers(this);
    }
}
