package cn.thinking.design.pattern.chapter02_Observer.theAnswer;

/**
 * 布告板--实现
 *
 * @author ken 2017-6-13 上午11:58:04
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {
	String temperature;
	String humidity;
	String pressure;
	private Subject subject;

	CurrentConditionsDisplay(Subject subject) {
		this.subject = subject;
		this.subject.registerObserver(this);
	}

	@Override
	public void update(String temperature, String humidity, String pressure, Object arg) {
		if (arg != null)
			System.out.println("CurrentConditionsDisplay:" + arg.getClass().getCanonicalName());
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		display();
	}

	@Override
	public void display() {
		System.out.println("CurrentConditionsDisplay，温度：" + temperature + "，湿度：" + humidity + "，气压：" + pressure);
	}

}
