package cn.thinking.design.pattern.chapter04_Factory.factoryMethod;

import cn.thinking.design.pattern.chapter04_Factory.factoryMethod.storeFactory.ChicagoPizzaStoreFactory;
import cn.thinking.design.pattern.chapter04_Factory.factoryMethod.storeFactory.NewYorkPizzaStoreFactory;
import cn.thinking.design.pattern.chapter04_Factory.factoryMethod.storeFactory.PizzaStoreFactory;

/**
 * 工厂方法模式
 * @author ken
 * 
 * @date 2017年6月27日 下午3:32:38
 * 
 */
public class Test {
	public static void main(String[] args) {
		PizzaStoreFactory nyStyle=new NewYorkPizzaStoreFactory();
		nyStyle.orderPizza("cheese");
		
		PizzaStoreFactory chicago=new ChicagoPizzaStoreFactory();
		chicago.orderPizza("cheese");
	}
	
}
