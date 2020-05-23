package cn.thinking.design.pattern.chapter07_Adapter.exzample;

public class MallardDuck implements Duck {

    @Override
    public void quack() {
        System.out.println("MallardDuck quack!");
    }

    @Override
    public void fly() {
        System.out.println("MallardDuck flying!");
    }

}
