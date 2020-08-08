package cn.thinking.design.pattern.chapter04_Factory.factoryMethod;

import org.apache.commons.lang3.StringUtils;

public class ChicagoStylePizzaStore extends PizzaStore {
	
	@Override
	protected Pizza createPizza(String type) {
		if(StringUtils.isBlank(type))
			throw new UnsupportedOperationException();
		if(type.equals("cheese"))
			return new ChicagoStyleCheesePizza();
		return null;
	}
}
