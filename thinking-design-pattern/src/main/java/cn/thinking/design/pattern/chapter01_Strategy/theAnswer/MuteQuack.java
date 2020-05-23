package cn.thinking.design.pattern.chapter01_Strategy.theAnswer;

public class MuteQuack implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("silence");
	}

}
