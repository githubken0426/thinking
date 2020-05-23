package cn.thinking.callback.part_4;

public class Seller implements DoJob {
	private String name;

	public Seller(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void fillBank(double a, double b, double result) {
		System.out.println(name + "求助小红计算:" + a + " + " + b + " = " + result);
	}

	// 计算方法
	public void callHelp(double a, double b) {
		System.out.println(name + "等待计算结果....");
		SuperCalculator.calcADD(a, b, this);
	}

	public static void main(String[] args) {
		Seller seller = new Seller("阿婆");
		seller.callHelp(12589.2, 12300.0);
	}
}
