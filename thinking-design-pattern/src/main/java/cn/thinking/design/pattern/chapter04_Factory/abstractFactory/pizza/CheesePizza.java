package cn.thinking.design.pattern.chapter04_Factory.abstractFactory.pizza;

import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredientFactory.PizzaIngredientFactory;
/**
 * 奶酪披萨
 * @author ken
 * 
 * @date 2017年7月10日 下午3:37:04
 */
public class CheesePizza extends Pizza {
	private PizzaIngredientFactory factory;

	public CheesePizza(PizzaIngredientFactory factory) {
		this.factory = factory;
	}

	@Override
	public void prepare() {
		System.out.println("preparing " + name);
		dough = factory.createDough();
		cheese = factory.createCheese();
		sauce = factory.createSauce();
	}

}
