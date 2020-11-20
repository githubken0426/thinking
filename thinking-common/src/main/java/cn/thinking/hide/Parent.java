package cn.thinking.hide;

public class Parent {
	public static int a = 100;
	public int b = 101;

	public static void methodA() {
		System.out.println("Parent methodA");
	}

	public void methodB() {
		System.out.println("Parent methodB");
	}

	public void methodC(Parent p) {
		System.out.println("Parent methodC:" + p);
	}

	public void methodD(Child c) {
		System.out.println("Parent methodD:" + c);
	}
	
	public void methodE() {
		System.out.println("Parent methodE!");
	}
}
