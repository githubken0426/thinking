package entity.serializable;

import java.io.Serializable;

/**
 * @Auther: kun.f.wang
 * @Date: 2/11/2019 13:54
 * @Description:
 */
public class Users implements Serializable {

    private static final long serialVersionUID = 3915486627490640161L;
    private int id;
    /**
     * 一个静态变量不管是否被transient修饰，均不能被序列化，
     * 反序列化后类中static型变量的值为当前JVM中对应static变量的值，这个值是JVM中的不是反序列化出的.
     */
    private static String userName;
    private transient String password;
    private Person person;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Users.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
