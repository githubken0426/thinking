package cn.thinking.design.pattern.chapter03_Decorator.me;

/**
 * 抽象组件
 * 根据杯子大小，决定tall增收20%,grande增收30%,venti增收40%
 * @author ken
 * 2017-6-15 下午02:55:56
 */
public abstract class Beverage {
	String description="tall增收20%,grande增收30%,venti增收40%";
	public abstract double cost();
	
	public String getDescription() {
		return description;
	}
	
	int size=0;
	public void setSize(int size) {
		this.size = size;
	}
	
	public double getDiscount() {
		switch (size) {
		case 1:
			System.out.println(1.2);
			return 1.2;
		case 2:
			System.out.println(1.3);
			return 1.3;
		case 3:
			System.out.println(1.4);
			return 1.4;
		default:
			return 1;
		}
	}
}
