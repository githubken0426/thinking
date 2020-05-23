package cn.thinking.cloneable;

class Students implements Cloneable {
    String name;
    int age;

    Students(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Teacher implements Cloneable {
    String name;
    int age;
    Students student;

    Teacher(String name, int age, Students student) {
        this.name = name;
        this.age = age;
        this.student = student;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Teacher teacher = (Teacher) super.clone();
        System.out.println("teacher.getClass() ==this.getClass():" + (teacher.getClass() == this.getClass()));
        System.out.println("teacher.equals(this):" + teacher.equals(this));
        /**
         * 此处如果不做clone()处理，student将指向同一个引用
         * (获取Teacher的clone对象后，对Teacher.student.age字段更改，student不会发生变化，也即是shallow copy)
         */
        //teacher.student=(Students) student.clone();
        return teacher;
    }

}

/**
 * shadow copy
 * java中clone的含义：
 * 假设x是一个非空对象,应该有:
 * 1)x.clone()!=x 为true,就是说他们不是同一个对象.
 * 2)x.clone().getClass()==x.getClass() 为true,他们是同一个类型Class.
 * 3)x.equals(x.clone()) 应该为false,两个对象.
 * clone方法是在Object种定义的,而且是protected型的,只有实现了这个接口,才可以在该类的实例上调用clone方法,否则会抛出CloneNotSupportException.
 * Object中默认的实现是一个浅拷贝,也就是表面拷贝,如果需要实现深层次拷贝的话,必须对类中可变域生成新的实例
 *
 * @author ken
 * @ClassName: ObjectCopyForCloneable
 * @Description:
 * @date 2018年3月15日 下午5:25:32
 */
public class ShadowCopyForCloneable {
    public static void main(String[] args) {
        Students student = new Students("晓明", 10);
        Teacher teacher = new Teacher("张老师", 30, student);
        try {
            Teacher t2 = (Teacher) teacher.clone();
            t2.student.age = 21;
            t2.student.name = "张明，(晓明的名称被更改了)";

            t2.age = 32;
            t2.name = "李老师";
            // t2 浅copy了teacher对象，teacher只有student引用，所以student变化引用的也变化
            System.out.println("学生名称:" + teacher.student.name + "，年龄" + teacher.student.age);
            System.out.println("老师名称:" + teacher.name + "，年龄" + teacher.age);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}