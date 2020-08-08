package cn.thinking.design.pattern.chapter04_Factory.factoryMethod;
/**
 * PizzaStore为一个超类型
 * 让子类来实现具体的Pizza制作方法
 * @author ken
 * 
 * @date 2017年6月27日 下午3:48:22
 */
public abstract class PizzaStore {
	/**
	 * 工厂方法，用来创建产品
	 * @param type
	 * @return
	 * @date 2017年6月27日 下午5:46:02
	 */
	protected abstract Pizza createPizza(String type);
	/**
	 * 传入字符串的方法容易造成错误。
	 * 可以考虑使用enum、静态常量等
	 * @param type
	 * @date 2018年9月13日 下午3:56:16
	 */
	public final Pizza orderPizza(String type){
		Pizza pizza=createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
}
