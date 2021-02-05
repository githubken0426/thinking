package cn.thinking.hide;

public class Parent {
	public static String a = "Parent var a";
	public String b = "Parent var b";

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
}
