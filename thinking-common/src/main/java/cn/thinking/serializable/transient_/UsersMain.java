package cn.thinking.serializable.transient_;

import cn.thinking.CommonConfiguration;
import entity.serializable.Users;
import entity.serializable.Person;

import java.io.*;

/**
 * @Auther: kun.f.wang
 * @Date: 2/11/2019 14:02
 * @Description:
 */
public class UsersMain {
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Person stu = new Person();
		stu.setAge(25);
		stu.setName("student 张三");
		Users user = new Users();
		user.setId(1);
		user.setUserName("张同學");
		user.setPassword("123465");
		user.setPerson(stu);

		System.out.println("序列化前姓名：" + user.getUserName());
		System.out.println("序列化前密码：" + user.getPassword());
		String path = CommonConfiguration.DESKTOP_PATH + File.separator + "User.txt";
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(path));
		os.writeObject(user);
		os.flush();
		os.close();

		Users.setUserName("序列化之后，修改变量的值为：李同学.");// 改变静态变量的值
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(path));
		Users readUser = (Users) is.readObject(); // 从流中读取User的数据
		is.close();

		System.out.println("\nRead After Serializable: ");
		System.err.println("password: " + readUser.getPassword());
		System.out.println("username: " + readUser.getUserName());
		System.out.println("序列化后的Student对象:" + readUser.getPerson().getName());

	}
}
