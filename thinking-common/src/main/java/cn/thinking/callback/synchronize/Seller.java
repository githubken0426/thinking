package cn.thinking.callback.synchronize;


import cn.thinking.callback.DoFillJob;
import cn.thinking.callback.SuperCalculator;

public class Seller implements DoFillJob {
	private String name;

	public Seller(String name) {
		this.name = name;
	}

	@Override
	public void fillBank(double a, double b, double result) {
		System.out.println(name + "回调函数计算结果:" + a + "+" + b + "=" + result);
	}

	public void callBack(double a, double b) {
		System.out.println(name + "问完问题后，等待结果！");
		SuperCalculator.add(a, b, this);
	}
}
