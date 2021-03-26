package cn.thinking.callback;

import java.util.concurrent.TimeUnit;

/**
 * 编程分为两类：系统编程（system programming）和应用编程（application programming）。
 * 所谓系统编程，简单来说，就是编写库；而应用编程就是利用写好的各种库来编写具某种功用的程序，也就是应用。
 * 系统程序员会给自己写的库留下一些接口，即API（application programming interface，应用编程接口），以供应用程序员使用。
 * 所以在抽象层的图示里，库位于应用的底下。当程序跑起来时，一般情况下，应用程序（application program）会时常通过API调用库里所预先备好的函数。
 * 但是有些库函数（library function）却要求应用先传给它一个函数，好在合适的时候调用，以完成目标任务。这个被传入的、后又被调用的函数就称为回调函数（callback function）。
 * 链接：https://www.zhihu.com/question/19801131/answer/27459821
 * 
 * 这一设计允许了底层代码调用在高层定义的子程序
 * 一是功能的模块化,通过传入不同的回调方法,实现功能动态切换
 * 二是流程的需要,方法被调用的时机不可控,只能被动调用.
 * 
 * 在回调中，主程序把回调函数像参数一样传入库函数。这样一来，只要我们改变传进库函数的参数，就可以实现不同的功能，这样有没有觉得很灵活？并且丝毫不需要修改库函数的实现，这就是解耦。
 * 再仔细看看，主函数和回调函数是在同一层的，而库函数在另外一层，想一想，如果库函数对我们不可见，我们修改不了库函数的实现，也就是说不能通过修改库函数让库函数调用普通函数那样实现，
 * 那我们就只能通过传入不同的回调函数了，这也就是在日常工作中常见的情况。
 * 
 * 动机：
 * 当我们设计软件的时候，我们常常考虑low level classes会实现基本和主要的操作，而high level classes用于封闭复杂逻辑。后者往往依赖于前者。
 * 可是当我们需要替换前者的时候，我们程度的可扩展性就变得很差。
 * 意图：
 * 1、High-level modules不应该依赖于low-level modules，它们应该依赖于抽象。
 * 2、抽象不应该依赖于细节，细节应该依赖于抽象。
 * @author kun.f.wang
 */
public class SuperCalculator {
    /**
     * 此处进行了函数的回调
     *
     * @param a
     * @param b
     */
    public static void add(double a, double b, DoFillJob fill) {
        try {
            System.out.println("计算器计算耗时10秒！");
            TimeUnit.SECONDS.sleep(10);
            fill.fillBank(a, b, a + b);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
