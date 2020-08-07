package cn.thinking.design.pattern.chapter_03Decorator.me;

public class Espresso extends Beverage {
	public Espresso() {
		description = "Espresso";
	}

	@Override
	public double cost() {
		return 2.9;
	}
}
