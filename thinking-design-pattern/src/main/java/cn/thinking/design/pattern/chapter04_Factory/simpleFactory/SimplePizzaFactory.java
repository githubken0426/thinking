package cn.thinking.design.pattern.chapter04_Factory.simpleFactory;

import org.apache.commons.lang3.StringUtils;

import cn.thinking.design.pattern.chapter04_Factory.factoryMethod.pizza.NewYorkCheesePizza;
import cn.thinking.design.pattern.chapter04_Factory.factoryMethod.pizza.Pizza;

/**
 * 简单工厂
 * @author ken
 * 
 * @date 2017年7月3日 下午5:44:04
 */
public class SimplePizzaFactory {
	/**
	 * 也可以创建静态方法(静态工厂)
	 * 优点:调用方法不用创建工厂对象
	 * 缺点:无法通过继承来改变创建方法的行为
	 * @param type
	 * @date 2018年9月13日 下午2:25:45
	 */
	public Pizza createPizza(String type) {
		if(StringUtils.isBlank(type))
			throw new UnsupportedOperationException();
		if(type.equals("cheese"))
			return new NewYorkCheesePizza();
		if(type.equals("other"))
			return null;
		return null;
	}
}
