package cn.thinking.callback.part_3;

/**
 * part3:使用计算类计算
 *
 * @author Administrator
 */
public class Student {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public Student(String name) {
        this.name = name;
    }

    //计算方法
    private void callHelp(double a, double b) {
        SuperCalculator.calcADD(a, b, this);
    }

    //回调方法
    public void fillBank(double a, double b, double result) {
        System.out.println(name + "使用计算器计算结果:" + a + "+" + b + "=" + result);
    }

    /**
     * 小明通过自身的callHelp方法调用了小红(new SuperCalculator())的calcADD方法，
     * 在调用的时候将自身的引用（this）当做参数一并传入，
     * 小红在使用计算器得出结果之后，回调了小明的fillBlank方法，将结果填在了黑板上的空格里
     *
     * @param args
     */
    public static void main(String[] args) {
        Student stu = new Student("mis zhang");
        stu.callHelp(1625.00, 4306.03);
    }
}