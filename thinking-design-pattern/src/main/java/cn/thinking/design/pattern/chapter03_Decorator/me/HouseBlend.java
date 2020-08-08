package cn.thinking.design.pattern.chapter03_Decorator.me;

/**
 * 具体组件
 * 
 * @author ken 2017-6-15 下午02:56:24
 */
public class HouseBlend extends Beverage {
	public HouseBlend() {
		description = "HouseBlend";
	}

	@Override
	public double cost() {
		return 3.6;
	}

}
