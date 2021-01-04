package cn.thinking.design.pattern.chapter04_Factory.factoryMethod.pizza;

public class NewYorkCheesePizza implements Pizza {

	@Override
	public void prepare() {
		System.out.println("New York CheesePizza prepare");
	}

	@Override
	public void bake() {
		System.out.println("bake");
	}

	@Override
	public void cut() {
		System.out.println("cut");
	}

	@Override
	public void box() {
		System.out.println("box");
	}
}
