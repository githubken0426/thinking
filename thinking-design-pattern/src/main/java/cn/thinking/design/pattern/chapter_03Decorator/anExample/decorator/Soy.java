package cn.thinking.design.pattern.chapter_03Decorator.anExample.decorator;

import cn.thinking.design.pattern.chapter_03Decorator.anExample.beverage.Beverage;

/**
 * 具体的装饰者
 * 
 * @author ken 2017-6-15 pm 03:08:04
 */
public class Soy extends CondimentDecorator {
	Beverage beverage;

	public Soy(Beverage beverage) {
		description = "soy";
		this.beverage = beverage;
	}

	@Override
	public double cost() {
		double result = beverage.cost();
		return 1.5 + result;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + ",Soy";
	}
}
