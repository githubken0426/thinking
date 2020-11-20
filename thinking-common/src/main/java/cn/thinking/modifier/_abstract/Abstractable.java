package cn.thinking.modifier._abstract;

/**
 * 1、语法层面上的区别
 * 1）抽象类可以提供成员方法的实现细节；接口中只能存在public abstract 方法；
 * 2）抽象类中的成员变量可以是各种类型的；接口中的成员变量只能是public static final类型的；
 * 3）抽象类可以有静态代码块和静态方法；接口中不能含有静态代码块以及静态方法；
 * 4）一个类只能继承一个抽象类；一个类却可以实现多个接口。
 */
/**
 * 2、设计层面上的区别 
 * 1） 抽象层次不同:抽象类是对一类事物的抽象，即对整个类整体进行抽象，包括属性、行为;而接口是对对类局部（行为）的抽象。 
 * 
 * 2）跨域不同：抽象类所跨域的是具有相似特点的类，而接口却可以跨域不同的类。
 * 抽象类是从子类中发现公共部分，然后泛化成抽象类，子类继承该父类即可；但是接口不同，实现它的子类可以不存在任何关系，共同之处。
 * 抽象类所体现的是一种继承关系，要想使得继承关系合理，父类和派生类在概念本质上应该是相同的,必须存在"is-a" 关系。
 * 对于接口则不然，并不要求接口的实现者和接口定义在概念本质上是一致的， 仅仅是实现了接口定义的契约而已。实现则是"like-a"的关系。
 * 
 * 抽象类，"is-a"关系，抽象出共同的本质特征，单一继承；接口，"like-a"关系，个性化特征，多重继承。
 * 
 * 3）设计层面不同:抽象类作为很多子类的父类，它是一种模板式设计。而接口是一种行为规范，它是一种辐射式设计。
 * 
 * @author kun.f.wang
 */
public abstract class Abstractable {

	public final static String str = "final_str";
	public int unI;
	public int i = 10;
	/**
	 * 交替构造器调用机制（alternate constructor invocation）
	 * 抽象类可以声明并定义构造函数。因为你不可以创建抽象类的实例，所以构造函数只能通过构造函数链调用（Java中构造函数链指的是从其他构造函数调用一个构造函数）.
	 * 
	 * 那么构造函数的作用是什么？
	 * 用来初始化抽象类内部声明的通用变量，并被各种实现使用。
	 * 即使你没有提供任何构造函数，编译器将为抽象类添加默认的无参数的构造函数(任何构造函数中的第一条语句隐式调用super（），Java中默认超类的构造函数).
	 */
	public Abstractable() {
		this(100);
	}

	public Abstractable(int param) {
		System.out.println("Abstractable constructor with param");
		System.out.println("before init:" + unI);
		unI = 1009;
		i = 100;
		System.out.println("after init:" + unI);
		System.out.println(i);
		System.out.println("*******init finished********");
	}

	public abstract void execute();
	
	public static void open(int a) {
		System.out.println("static void open(int a):" + a);
	}

	public void close() {
		System.out.println("close");
	}
}
