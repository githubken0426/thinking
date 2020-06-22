package cn.thinking.design.pattern.chapter02_Observer.theAnswer;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {
    private List<Observer> observers;
    String temperature;
    String humidity;
    String pressure;

    WeatherData() {
        observers = new ArrayList<Observer>();
    }
    /**
     *  主题对象向观察者推送主题的详细信息，不管观察者是否需要，推送的信息通常是主题对象的全部或部分数据。
     *  ●　　推模型　 主题对象向观察者推送主题的详细信息，不管观察者是否需要，推送的信息通常是主题对象的全部或部分数据。
     *  ●　　拉模型
     *  主题对象在通知观察者的时候，只传递少量信息。如果观察者需要更具体的信息，由观察者主动到主题对象中获取，相当于是观察者从主题对象中拉数据。
     *  一般这种模型的实现中，会把主题对象自身通过update()方法传递给观察者，这样在观察者需要获取数据的时候，就可以通过这个引用来获取了.
     */
    @Override
    public void notifyObserver(Object arg) {
    	System.out.println("notifyObserver(Object arg):"+arg);
        for (Observer ob : observers) {
            ob.update(temperature, humidity, pressure,arg);
        }
    }

    @Override
    public void registerObserver(Observer ob) {
        if (!observers.contains(ob))
            observers.add(ob);
    }

    @Override
    public void removeObserver(Observer ob) {
        if (observers.contains(ob))
            observers.remove(ob);
    }

    /**
     * 当从气象站观测到新的信息通知观察者
     *
     * @param temperature
     * @param humidity
     * @param pressure    2017-6-13 下午01:52:42
     */
    @Override
    public void setMeasurements(String temperature, String humidity, String pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObserver(this);
//        notifyObserver();
    }
}
