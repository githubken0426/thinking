package cn.thinking.design.pattern.chapter03_Decorator.me;

public class Whip extends CondimentDecorator {
	Beverage beverage;

	public Whip(Beverage beverage) {
		description = "whip";
		this.beverage = beverage;
	}

	@Override
	public double cost() {
		double discount = getDiscount();
		return (1+ beverage.cost()) * discount;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + ",Whip";
	}
}
