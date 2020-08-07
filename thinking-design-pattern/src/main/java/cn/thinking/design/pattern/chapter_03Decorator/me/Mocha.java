package cn.thinking.design.pattern.chapter_03Decorator.me;

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
		double discount = getDiscount();
		return (1 + beverage.cost()) * discount;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + ",Mocha";
	}

}
