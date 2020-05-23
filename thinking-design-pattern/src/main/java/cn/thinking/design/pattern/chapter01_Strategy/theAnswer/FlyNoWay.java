package cn.thinking.design.pattern.chapter01_Strategy.theAnswer;

public class FlyNoWay implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("I can not fly!");
	}

}
