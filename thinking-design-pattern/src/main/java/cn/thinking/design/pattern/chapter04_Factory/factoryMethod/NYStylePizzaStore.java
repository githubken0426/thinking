package cn.thinking.design.pattern.chapter04_Factory.factoryMethod;

import org.apache.commons.lang3.StringUtils;

public class NYStylePizzaStore extends PizzaStore {

	@Override
	protected Pizza createPizza(String type) {
		if(StringUtils.isBlank(type))
			throw new UnsupportedOperationException();
		if(type.equals("cheese"))
			return new NYStyleCheesePizza();
		return null;
	}

}
