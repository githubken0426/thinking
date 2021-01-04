package cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredientFactory;

import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredient.chicago.ChicagoCheese;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredient.chicago.ChicagoDough;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredient.chicago.ChicagoSauce;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredient.Cheese;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredient.Dough;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredient.Sauce;
/**
 * 芝加哥原料工厂
 * @author ken
 * 
 * @date 2017年7月10日 下午3:14:57
 */
public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {

	@Override
	public Dough createDough() {
		System.out.println("添加芝加哥风味面团");
		return new ChicagoDough();
	}

	@Override
	public Cheese createCheese() {
		System.out.println("添加芝加哥风味奶酪");
		return new ChicagoCheese();
	}

	@Override
	public Sauce createSauce() {
		System.out.println("添加芝加哥风味酱");
		return new ChicagoSauce();
	}

}
