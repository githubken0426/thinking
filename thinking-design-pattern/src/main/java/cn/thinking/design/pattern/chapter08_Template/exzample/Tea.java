package cn.thinking.design.pattern.chapter08_Template.exzample;

public class Tea extends CaffeineBeverage {

	@Override
	void brew() {
		System.out.println("Steeping the tea!");
	}

	@Override
	void addCondiments() {
		System.out.println("Adding lemon!");
	}

}
