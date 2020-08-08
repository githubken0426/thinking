package cn.thinking.design.pattern.chapter04_Factory.anExample;

import cn.thinking.design.pattern.chapter04_Factory.factoryMethod.Pizza;

/**
 * PizzaStore为一个超类型
 * 让子类来实现具体的Pizza制作方法
 * @author ken
 * 
 * @date 2017年6月27日 下午3:48:22
 */
public class PizzaStore {
	private SimplePizzaFactory factory;
	public PizzaStore(SimplePizzaFactory factory){
		this.factory=factory;
	}
	
	public final Pizza orderPizza(String type){
		Pizza pizza=factory.createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
}
