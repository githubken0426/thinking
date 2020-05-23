package cn.thinking;

import java.io.Serializable;

/**
 * @Auther: kun.f.wang
 * @Date: 2/11/2019 13:55
 * @Description:
 */
public class Person implements Serializable {
    /**
     * private static final long serialVersionUID = 1L;
     * 版本信息一般在文件的开头声明，这是因为程序必须在处理文件之前首先检查文件的版本，除非确定了文件的版本，否则不必读取文件的其余部分.
     * serialVersionUID 用来表明类的不同版本间的兼容性。
     * <p>
     * 有两种生成方式：
     * 一种是默认的1L；
     * <p>
     * 另一种是根据类名、接口名、成员方法及属性等来生成一个64位的哈希字段.
     * 可以利用JDK的bin目录下的serialver.exe工具产生这个serialVersionUID 的值，
     * Person.class，执行命令：serialver Person，这时JVM会生成一个哈希字段.
     * 如果没有serialVersionUID，当类中属性变动(新增或减少)时候，再次读取序列化文档，将会报错:java.io.InvalidClassException
     * <p>
     * 序列化两种方式:只有实现了Serializable或Externalizable接口的类的对象才能被序列化。
     * Externalizable接口继承自Serializable接口，实现Externalizable接口的类完全由自身来控制序列化的行为，
     * 而仅实现Serializable接口的类可以采用默认的序列化方式
     * <p>
     * 序列化机制只保存对象的类型信息，属性的类型信息和属性值，和方法没有什么关系.
     */
    private static final long serialVersionUID = 1L;
    private int age;
    private String name;

    public Person() {
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "age:" + age + ",name:" + name;
    }
}
