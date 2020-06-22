package cn.thinking.design.pattern.chapter02_Observer.theAnswer;

/**
 * 观察者接口
 * Observer 模式的定义：
 * 	该模式定义了对象之间的一(Subject)对多(Observer)依赖关系。
 * 	当 Subject 对象的状态发生改变时，所有依赖于该 Subject 对象的 Observer 对象都会得到通知，并且自动更新。
 * 
 * 1.定义了对象间的一对多依赖关系；
 * 2.当 Subject 对象的状态发生改变时，所有依赖于该 Subject 对象的 Observer 对象都会得到通知；
 * 3.Observer 对象得到通知后，会自动更新，而不是被动；
 * 
 * 其它的所有点都是细枝末节，由具体业务需求来决定。比如：
 * 1. Subject 角色是应该定义成类？比如 内置的 java.util.Observable；还是应该定义成接口，以规避Java不支持多重继承的问题？比如《Head First 设计模式》中的推荐作法。
 * 2.应该在什么时候订阅主题（或者说注册观察者）？是实例化观察者对象的同时？还是由客户自主决定？。
 * 3.是否应该实现取消订阅功能（或者说取消注册）？
 * 4.主题对象通知观察者时，是否携带消息？换句话说，是“推”消息？还是“拉”消息？
 * 5.是否支持多线程？
 * @author ken
 * 2017-6-13 上午11:33:38
 */
public interface Observer {
    void update(String temperature, String humidity, String pressure,Object arg);
}
