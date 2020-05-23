package cn.thinking.design.pattern.chapter02_Observer.theAnswer;

/**
 * 主题
 *
 * @author ken
 * 2017-6-13 上午10:52:45
 */
public interface Subject {
    /**
     * 注册主题
     * <p>
     * 2017-6-13 上午11:06:13
     */
    void registerObserver(Observer ob);

    /**
     * 移除主题
     * <p>
     * 2017-6-13 上午11:06:23
     */
    void removeObserver(Observer ob);

    /**
     * 更改主题
     * <p>
     * 2017-6-13 上午11:06:33
     */
    void notifyObserver();

    void setMeasurements(String temperature, String humidity, String pressure);
}
