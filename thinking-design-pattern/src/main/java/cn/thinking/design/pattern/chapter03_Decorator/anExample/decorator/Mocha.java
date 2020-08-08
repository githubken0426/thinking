package cn.thinking.design.pattern.chapter03_Decorator.anExample.decorator;

import cn.thinking.design.pattern.chapter03_Decorator.anExample.beverage.Beverage;

/**
 * 具体的装饰者
 * 
 * @author ken 2017-6-15 pm 03:08:04
 */
public class Mocha extends CondimentDecorator {
	Beverage beverage;

	public Mocha(Beverage beverage) {
		description = "mocha";
		this.beverage = beverage;
	}

	@Override
	public double cost() {
		double result = beverage.cost();
		return 1.0 + result;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + ",Mocha";
	}

}
