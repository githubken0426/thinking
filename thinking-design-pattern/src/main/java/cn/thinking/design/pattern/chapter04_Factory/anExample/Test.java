package cn.thinking.design.pattern.chapter04_Factory.anExample;

/**
 * 简单模式
 * @author ken
 * 
 * @date 2017年6月27日 下午3:32:38
 * 
 */
public class Test {
	public static void main(String[] args) {
		PizzaStore nyStyle=new PizzaStore(new SimplePizzaFactory());
		nyStyle.orderPizza("cheese");
	}
	
}
