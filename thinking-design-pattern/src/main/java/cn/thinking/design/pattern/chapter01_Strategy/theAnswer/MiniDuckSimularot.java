package cn.thinking.design.pattern.chapter01_Strategy.theAnswer;

public class MiniDuckSimularot {
	public static void main(String[] args) {
		Duck mullard=new MallardDuck();
		mullard.setFly(new FlyWithWings());
		mullard.setQuack(new Quack());
		
		mullard.display();
		mullard.performQuack();
		mullard.performFly();
		
		System.out.println("**********************");
		Duck modelDuck = new Duck() {
			@Override
			public void display() {
				System.out.println("I am a model duck!");
			}
		};
		modelDuck.setFly(new FlyBehavior() {
			@Override
			public void fly() {
				System.out.println("I can not fly!");
			}
		});
		modelDuck.setQuack(new MuteQuack());
		modelDuck.display();
		modelDuck.performFly();
		modelDuck.performQuack();
	}
}
