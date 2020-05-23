package cn.thinking.design.pattern.chapter02_Observer.theAnswer;

/**
 * 观察者接口
 *
 * @author ken
 * 2017-6-13 上午11:33:38
 */
public interface Observer {
    void update(String temperature, String humidity, String pressure);
}
