package cn.thinking.design.pattern.chapter07_Adapter.exzample;

public class MainTestDrive {
    public static void main(String[] args) {
        Turkey turkey = new WildTurkey();
        Duck adapterDuck = new TurkeyAdapter(turkey);
        adapterDuck.quack();
        adapterDuck.fly();

        adapterDuck = new MallardDuck();
        adapterDuck.quack();
        adapterDuck.fly();
    }
}
