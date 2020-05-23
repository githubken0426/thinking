package cn.thinking.serializable.externl;

import entity.serializable.Person;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

/**
 * @Auther: kun.f.wang
 * @Date: 2/11/2019 15:17
 * @Description:
 */
public class UserExternl implements Externalizable {
    /**
     * 版本号
     */
    private static final long serialVersionUID = -4207483236608327184L;

    private int id;
    private String name;
    /**
     * 实现了Exeternalizble变量无论是否被transient修饰，
     * 如果writeExternal()方法中序列化了此变量，变量还是会被序列化
     */
    private transient String password;
    private Person student;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person getStudent() {
        return student;
    }

    public void setStudent(Person student) {
        this.student = student;
    }

    //当序列化对象时,该方法自动调用
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("现在执行序列化方法");
        //可以在序列化时写非自身的变量
        Date d = new Date();
        out.writeObject(d);
        out.writeObject(id);
        out.writeObject(name);
        out.writeObject(password);
        out.writeObject(student);
    }

    /**
     * 当反序列化对象时,该方法自动调用
     * 反序列化方法中的属性必须和序列化中的属性先后顺序一直
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("执行反序列化方法");
        Date d = (Date) in.readObject();
        System.out.println(d);

        this.id = (Integer) in.readObject();
        this.name = (String) in.readObject();
        this.password = (String) in.readObject();
        this.student = (Person) in.readObject();
    }

    public String toString() {
        return "用户名: " + this.name + ";学生姓名：" + this.student.getName() + ",密码:" + this.password;
    }
}
