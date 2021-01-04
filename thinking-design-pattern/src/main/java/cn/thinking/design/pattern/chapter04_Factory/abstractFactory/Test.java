package cn.thinking.design.pattern.chapter04_Factory.abstractFactory;

import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.storeFactory.ChicagoPizzaStoreFactory;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.storeFactory.NewYorkPizzaStoreFactory;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.storeFactory.PizzaStoreFactory;

/**
 * 工厂方法使用的是类(继承)
 * 抽象工厂使用的是对象(组合)
 * @author ken
 * 
 * @date 2017年7月10日 下午4:21:21
 */
public class Test {
	public static void main(String[] args) {
		PizzaStoreFactory store = new NewYorkPizzaStoreFactory();
		store.orderPizza("cheese");
		store.orderPizza("veggie");
		
		store = new ChicagoPizzaStoreFactory();
		store.orderPizza("veggie");
	}
}
