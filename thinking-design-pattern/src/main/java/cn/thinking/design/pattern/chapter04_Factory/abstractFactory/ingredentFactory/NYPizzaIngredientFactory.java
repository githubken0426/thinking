package cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredentFactory;

import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredent.Cheese;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredent.Dough;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredent.Sauce;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredent.ny.NYCheese;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredent.ny.NYDough;
import cn.thinking.design.pattern.chapter04_Factory.abstractFactory.ingredent.ny.NYSauce;
/**
 * 纽约原料工厂
 * @author ken
 * 
 * @date 2017年7月10日 下午3:14:25
 */
public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

	@Override
	public Dough createDough() {
		System.out.println("添加纽约风味面团");
		return new NYDough();
	}

	@Override
	public Cheese createCheese() {
		System.out.println("添加纽约风味奶酪");
		return new NYCheese();
	}

	@Override
	public Sauce createSauce() {
		System.out.println("添加纽约风味酱");
		return new NYSauce();
	}

}
