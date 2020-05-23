package cn.thinking.callback.part_1;

/**
 * part1：心算
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
    private double calcADD(double a, double b) {
        return a + b;
    }

    //填空方法
    public void fillBank(double a, double b) {
        System.out.println(name + "的计算结果:" + a + "+" + b + "=" + calcADD(a, b));
    }

    /**
     * 该过程完全由Student类的实例对象单独完成，并未涉及回调机制
     *
     * @param args
     */
    public static void main(String[] args) {
        Student stu = new Student("小明");
        stu.fillBank(15.00, 36.03);
    }
}