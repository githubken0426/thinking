package cn.thinking.initialize;

public class ExtendsMain {
    public static void main(String[] args) {
        B b = new B();
    }

    static class A {
        static {
            System.out.println("A的静态块");
        }

        A() {
            System.out.println("A的构造");
        }
    }

    static class B extends A {
        static {
            System.out.println("B的静态块");
        }

        B() {
            System.out.println("B的构造");
        }
    }
}
