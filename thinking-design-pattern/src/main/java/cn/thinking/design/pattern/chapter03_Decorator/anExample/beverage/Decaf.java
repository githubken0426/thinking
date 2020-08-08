package cn.thinking.design.pattern.chapter03_Decorator.anExample.beverage;

public class Decaf extends Beverage {
	public Decaf() {
		description = "Decaf";
	}

	@Override
	public double cost() {
		return 4.0;
	}

}
