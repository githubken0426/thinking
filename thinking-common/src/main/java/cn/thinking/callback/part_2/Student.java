package cn.thinking.callback.part_2;

/**
 * part2:使用计算类计算
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
    private double useCalculator(double a, double b) {
        return Calculator.calcADD(a, b);
    }

    //填空方法
    public void fillBank(double a, double b) {
        System.out.println(name + "使用计算器计算结果:" + a + "+" + b + "=" + useCalculator(a, b));
    }

    /**
     * 该过程中仍未涉及到回调机制，
     * 但是部分小明的部分工作已经实现了转移，由计算器来协助实现
     *
     * @param args
     */
    public static void main(String[] args) {
        Student stu = new Student("小明");
        stu.fillBank(165.00, 306.03);
    }
}