package cn.thinking.design.pattern.chapter08_Template.exzample;

public class MainTest {
	public static void main(String[] args) {
		CaffeineBeverage caffeine=new Tea();
		caffeine.prepare();
		
		System.out.println();
		caffeine=new Coffee();
		caffeine.prepare();
	}
}
