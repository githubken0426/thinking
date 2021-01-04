package cn.thinking.design.pattern.chapter04_Factory.simpleFactory;

/**
 * 简单工厂中使用反射创建生成对象也是一种不错的办法，但反射也是从类名（Class Name）反射而不能从类(Class)反射！
 * 
 * 1、先看一下工厂模式是用来干什么的----属于创建模式，解决子类创建问题。
 * 换句话来说，调用者并不知道运行时真正的类名，只知道从“Circle"可以创建出一个shape接口的类，至于类的名称是否叫'Circle"，调用者并不知情。
 * 所以真正的对工厂进行扩展的方式（防止程序员调用出错）可以考虑使用一个枚举类（防止传入参数时，把circle拼写错误）。
 * 
 * 2、其实在.net类库中存在这个模式的的一个典型的。但他引入的另一个概念“可插入编程协议”----那就是WebRequest。
 * WebRequest req = WebRequest.Create("http://pizza.store.com");可以自动创建一个HttpWebRequest的对象;
 * 当然，如果你给定的是一个WebRequest.Create("ftp://pizza.store.com")地址，他会自动创建一个FtpWebRequest对象。
 * 工厂模式中着重介绍的是这种通过某个特定的参数，让你一个接口去干对应不同的事而已！而不是让调用者知道了类！
 * 
 * 3、如果调用者参肯定类型是Circle的话，那么其工厂没有存在的意义了！比如 IShape shape = new Circle();这样不是更好？
 * 也就是说调用者有了Circle这个知识是可以直接调用的，根据DP（迪米特法则）其实调用者并不知道有一个Circle类的存在，
 * 他只需要知道这个IShape接口可以计算圆面积，而不需要知道圆这个类到底是什么类名——他只知道给定一个”circle"字符串的参数,
 * IShape接口可以自动计算圆的面积就可以了！比如如果圆的那个类名叫"CircleShape“呢？
 * 
 * 4、不管是反射还是泛型都干扰了你们具体类的生成！其实这个要说明的问题就是这个，调用者（clinet)只知道IShape的存在，在创建时给IShape一个参数"Circle",
 * 它可以计算圆的面积之类的工作，但是为什么会执行这些工作，根据迪米特法则，client是不用知道的。用反射或泛型转嫁给了调用者（clinet)，那么，这种情况下，要工厂类何用？
 * 如果所有从IShape继承的类都是Internal类型的呢？而client肯定不会与IShape一个namespace！这时，你会了现你根本无法拿到这个类名！
 * 
 * 5、Create时使用注册机制是一种简单的办法，比如使用一个枚举类，把功能总结到一处；
 * 而反射也是一种最简单的办法，调用者输入的名称恰是类名称或某种规则时使用，
 * 比如调用者输入的是Circle，而类恰是CircleShape，那么可以通过输入+”Shape"字符串形成新的类名，然后从字符串将运行类反射出来！
 * @author ken
 * @date 2017年6月27日 下午3:32:38
 */
public class Test {
	public static void main(String[] args) {
		PizzaStore nyStyle=new PizzaStore(new SimplePizzaFactory());
		nyStyle.orderPizza("cheese");
	}
	
}
