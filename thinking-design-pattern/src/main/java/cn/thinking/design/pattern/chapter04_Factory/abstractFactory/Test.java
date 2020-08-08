package cn.thinking.design.pattern.chapter04_Factory.abstractFactory;

import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.store.ChicagoPizzaStore;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.store.NYPizzaStore;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.store.PizzaStore;

/**
 * 工厂方法使用的是类(继承)
 * 抽象工厂使用的是对象(组合)
 * @author ken
 * 
 * @date 2017年7月10日 下午4:21:21
 */
public class Test {
	public static void main(String[] args) {
		PizzaStore store=new NYPizzaStore();
		store.orderPizza("cheese");
		
		store=new ChicagoPizzaStore();
		store.orderPizza("veggie");
	}
}
