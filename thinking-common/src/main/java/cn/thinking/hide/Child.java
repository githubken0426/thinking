package cn.thinking.hide;

public class Child extends Parent {
	public  String a = "Child var a";
	public static String b = "Child var b";

	/**
	 * 隐藏：父类和子类拥有相同名字的属性或者方法时（方法隐藏只有一种：父类和子类存在相同的静态方法），父类的同名的属性或者方法形式上不见了，实际是还是存在的。
	 * 
	 * 隐藏是对于静态方法和成员变量（静态变量和实例变量）而言的: 
	 * （1）当发生隐藏的时候，声明类型是什么类，就调用对应类的属性或者方法，而不会发生动态绑定。
	 * （2）属性只能被隐藏，不能被覆盖 
	 * （3）变量可以交叉隐藏：子类实例变量/静态变量可以隐藏父类的实例/静态变量
	 * 
	 * （4）方法不能交叉覆盖
	 * 	子类实例方法不能覆盖父类的静态方法
	 *	@Override The method methodA() of type Child must override or implement a supertype method
	 * 
	 * 	子类的静态方法也不能覆盖父类的实例方法：
	 * 	This instance method cannot override the static method from Parent.
	 */
	public static void methodA() {
		System.out.println("Child methodA");
	}

	/**
	 * 子类继承父类后，父类的非静态方法被子类重写后覆盖上去，通过相应的引用也访问不到了（除非创建父类的对象来调用）。这种情况称为覆盖。
	 */
	@SuppressWarnings("static-access")
	@Override
	public void methodB() {
		super.methodA();
		System.out.println("Parent a:" + super.a);
		System.out.println("Parent b:" + super.b);
		System.out.println("Child methodB");
	}

	@Override
	public void methodC(Parent p) {
		System.out.println("Child methodC: " + p);
	}

	@Override
	public void methodD(Child c) {
		System.out.println("Child methodD: " + c);
	}
}
