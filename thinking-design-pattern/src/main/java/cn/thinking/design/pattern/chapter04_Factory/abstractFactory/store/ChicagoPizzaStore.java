package cn.thinking.design.pattern.chapter04_Factory.abstractFactory.store;

import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredentFactory.ChicagoPizzaIngredientFactory;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredentFactory.PizzaIngredientFactory;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.pizza.CheesePizza;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.pizza.Pizza;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.pizza.VeggiePizza;

/**
 * 芝加哥披萨店
 * @author ken
 * 
 * @date 2017年7月10日 下午3:28:30
 */
public class ChicagoPizzaStore extends PizzaStore{

	
	@Override
	public Pizza createPizza(String type) {
		Pizza pizza=null;
		PizzaIngredientFactory factory=new ChicagoPizzaIngredientFactory();
		if(type.equals("cheese")){
			pizza= new CheesePizza(factory);
			pizza.setName("芝加哥cheese披萨！");
		}else if(type.equals("veggie")){
			pizza= new VeggiePizza(factory);
			pizza.setName("芝加哥veggie披萨！");
		}
		return pizza;
	}

}