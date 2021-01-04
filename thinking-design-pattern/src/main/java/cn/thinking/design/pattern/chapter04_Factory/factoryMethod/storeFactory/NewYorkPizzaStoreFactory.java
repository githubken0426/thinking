package cn.thinking.design.pattern.chapter04_Factory.factoryMethod.storeFactory;

import org.apache.commons.lang3.StringUtils;

import cn.thinking.design.pattern.chapter04_Factory.factoryMethod.pizza.NewYorkCheesePizza;
import cn.thinking.design.pattern.chapter04_Factory.factoryMethod.pizza.Pizza;

public class NewYorkPizzaStoreFactory extends PizzaStoreFactory {

	@Override
	protected Pizza createPizza(String type) {
		if(StringUtils.isBlank(type))
			throw new UnsupportedOperationException();
		if(type.equals("cheese"))
			return new NewYorkCheesePizza();
		return null;
	}

}
