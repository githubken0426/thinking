package cn.thinking.design.pattern.chapter03_Decorator.anExample.beverage;

/**
 * DarkRoast组件
 * 
 * @author kun.f.wang
 */
public class DarkRoast extends Beverage {
	public DarkRoast() {
		description = "DarkRost";
	}

	@Override
	public double cost() {
		return 5;
	}

}
