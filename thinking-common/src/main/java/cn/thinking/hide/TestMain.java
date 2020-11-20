package cn.thinking.hide;

/**
 * 任何一个引用变量都有两个类型：一个叫静态类型，也就是定义该引用变量的类型；另一个叫动态类型，也就是该引用实际指向的对象类型。
 * 比如对于两个类A和类B，有：A a=new B()；引用a的静态类型就是A，动态类型就是B。
 * 
 * java中引用的静态类型在编译的时候就可以确认，但是编译器无法得知这个引用的动态类型；只有当程序运行时，通过RTTI就可以检查出引用的动态类型。
 * java中绑定的概念：对于一个程序，在调用一个方法的时候，如何将一个方法和该方法所在的类关联起来，这就是绑定。
 * java中的绑定分为静态绑定和动态绑定。
 * 静态绑定：所有依赖于静态类型来将某方法和该方法所在的类关联起来的动作都是静态绑定。因为静态绑定在程序运行前发生，所有又叫前期绑定。
 * 动态绑定：所有依赖于动态类型来将某方法和该方法所在的类关联起来的动作都是动态绑定。因为动态绑定是在程序运行时，通过RTTI实现，所以又叫后期绑定。
 * 
 * java中类的属性也都是静态绑定的。这是因为静态绑定是有很多的好处，它可以让我们在编译期就发现程序中的错误，而不是在运行期。
 * 这样就可以提高程序的运行效率！而对方法采取动态绑定是为了实现多态。
 * @author kun.f.wang
 */
@SuppressWarnings("static-access")
public class TestMain {
	public static void main(String[] args) {
		Parent instance = new Child();
		//Invoke Parent properties & method
		System.out.println(instance.a);
		System.out.println(instance.b);
		instance.methodA();
		
		instance.methodB();
		instance.methodC(instance);
		instance.methodD(new Child());
	}
}
