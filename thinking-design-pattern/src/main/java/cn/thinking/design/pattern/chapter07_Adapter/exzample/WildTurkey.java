package cn.thinking.design.pattern.chapter07_Adapter.exzample;

public class WildTurkey implements Turkey {

    @Override
    public void quack() {
        System.out.println("WildTurkey quack!");
    }

    @Override
    public void fly() {
        System.out.println("WildTurkey flying!");
    }

}
