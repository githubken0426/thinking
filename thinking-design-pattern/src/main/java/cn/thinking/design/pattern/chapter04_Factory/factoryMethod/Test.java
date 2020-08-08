package cn.thinking.design.pattern.chapter04_Factory.factoryMethod;

/**
 * 工厂方法模式
 * @author ken
 * 
 * @date 2017年6月27日 下午3:32:38
 * 
 */
public class Test {
	public static void main(String[] args) {
		PizzaStore nyStyle=new NYStylePizzaStore();
		nyStyle.orderPizza("cheese");
		
		PizzaStore chicago=new ChicagoStylePizzaStore();
		chicago.orderPizza("cheese");
	}
	
}
