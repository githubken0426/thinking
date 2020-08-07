package cn.thinking.design.pattern.chapter02_Observer.theAnswer;

/**
 * 主题
 *
 * @author ken
 * 2017-6-13 10:52:45
 */
public interface Subject {
    /**
            * 注册主题
     * 2017-6-13 11:06:13
     */
    void registerObserver(Observer ob);

    /**
            * 移除主题
     * 2017-6-13 11:06:23
     */
    void removeObserver(Observer ob);

    /**
            * 更改主题
     * 2017-6-13 11:06:33
     */
    void notifyObserver(Object arg);
    
    default void notifyObserver() {
    	notifyObserver(null);
    }

    void setMeasurements(String temperature, String humidity, String pressure);
}
