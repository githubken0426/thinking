package cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredientFactory;

import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredient.newyork.NewYorkCheese;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredient.newyork.NewYorkDough;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredient.newyork.NewYorkSauce;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredient.Cheese;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredient.Dough;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredient.Sauce;
/**
 * 纽约原料工厂
 * @author ken
 * 
 * @date 2017年7月10日 下午3:14:25
 */
public class NewYorkPizzaIngredientFactory implements PizzaIngredientFactory {

	@Override
	public Dough createDough() {
		System.out.println("添加纽约风味面团");
		return new NewYorkDough();
	}

	@Override
	public Cheese createCheese() {
		System.out.println("添加纽约风味奶酪");
		return new NewYorkCheese();
	}

	@Override
	public Sauce createSauce() {
		System.out.println("添加纽约风味酱");
		return new NewYorkSauce();
	}

}
