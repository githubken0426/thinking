package cn.thinking.design.pattern.chapter01_Strategy.theAnswer;


public abstract class Duck {
	/**
	 * 策略模式：
	 *  定义了算法组，分别封装起来，算法之间可以相互替换(使用共同的接口，算法可以替换)。算法的变化独立于使用算法的客户；
	 * 组合(has a关系):每个鸭子都有一个FlyBehavior和有一个QuackBehavior行为，鸭子的行为不是继承来的，
	 * 				  是通过FlyBehavior和QuackBehavior组合到鸭子中的。
	 * 委托(代理):行为的实现委托给具体的实现.
	 */
	FlyBehavior fly;
	QuackBehavior quack;
	
	public void swim() {
		System.out.println("会游");
	}

	public abstract void display();
	
	
	public void setFly(FlyBehavior fly) {
		this.fly = fly;
	}

	public void setQuack(QuackBehavior quack) {
		this.quack = quack;
	}

	void performQuack(){
		quack.quack();
	}
	void performFly(){
		fly.fly();
	}
}
