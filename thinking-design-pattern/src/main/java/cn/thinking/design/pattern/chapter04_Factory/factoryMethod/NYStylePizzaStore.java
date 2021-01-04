package cn.thinking.design.pattern.chapter04_Factory.factoryMethod;

import org.apache.commons.lang3.StringUtils;

import cn.thinking.design.pattern.chapter04_Factory.factoryMethod.pizza.NewYorkStyleCheesePizza;
import cn.thinking.design.pattern.chapter04_Factory.factoryMethod.pizza.Pizza;

public class NYStylePizzaStore extends PizzaStore {

	@Override
	protected Pizza createPizza(String type) {
		if(StringUtils.isBlank(type))
			throw new UnsupportedOperationException();
		if(type.equals("cheese"))
			return new NewYorkStyleCheesePizza();
		return null;
	}

}
