package cn.thinking.design.pattern.chapter07_Adapter.exzample;

/**
 * 适配器模式分为:对象适配器(使用组合) 类适配器(使用继承，需要多重继承，java不可用)
 *
 * @author ken
 * @date 2017年7月12日 下午1:32:13
 */
public class TurkeyAdapter implements Duck {
    // 获取适配对象的引用
    Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.quack();
    }

    @Override
    public void fly() {
        turkey.fly();
    }

}
