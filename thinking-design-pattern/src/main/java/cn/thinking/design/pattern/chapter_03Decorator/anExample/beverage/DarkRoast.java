package cn.thinking.design.pattern.chapter_03Decorator.anExample.beverage;

public class DarkRoast extends Beverage {
	public DarkRoast() {
		description = "DarkRost";
	}

	@Override
	public double cost() {
		return 5;
	}

}
