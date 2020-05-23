package cn.thinking.design.pattern.chapter01_Strategy.theAnswer;


public class FlyWithWings implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("I am flying!");
	}

}
