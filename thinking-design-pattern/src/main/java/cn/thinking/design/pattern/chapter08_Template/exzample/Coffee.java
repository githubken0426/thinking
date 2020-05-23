package cn.thinking.design.pattern.chapter08_Template.exzample;

public class Coffee extends CaffeineBeverage {

	@Override
	void brew() {
		System.out.println("Dripping coffee!");
	}

	@Override
	void addCondiments() {
		System.out.println("Adding sugar and milk!");
	}

}
