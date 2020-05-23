package cn.thinking.callback.part_4;

/**
 * part2:使用计算类计算
 * 
 * @author Administrator
 *
 */
public class Student implements DoJob {
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public Student(String name) {
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

	/**
	 * 小红希望以后不仅向小名提供计算服务， 还希望向班里的其他小朋友们提供计算服务，甚至以后能够拓展其他人的业务，
	 * 于是她向所有的顾客约定了一个办法，用于统一的处理。 这个统一的方法，小红做成了一个接口，提供给了大家。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Student stu = new Student("小明");
		stu.callHelp(165.00, 306.03);
	}
}
