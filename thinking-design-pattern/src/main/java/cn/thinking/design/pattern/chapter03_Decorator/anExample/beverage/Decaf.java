package cn.thinking.design.pattern.chapter03_Decorator.anExample.beverage;

/**
 * Decaf组件
 * 
 * @author kun.f.wang
 */
public class Decaf extends Beverage {
	public Decaf() {
		description = "Decaf";
	}

	@Override
	public double cost() {
		return 4.0;
	}

}
