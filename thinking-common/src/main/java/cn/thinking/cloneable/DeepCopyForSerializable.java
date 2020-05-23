package cn.thinking.cloneable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;

/**
 * 实现深度copy 也可以使用序列化，但序列化很耗时。
 * 在一些框架中，我们便可以感受到，它们往往将对象进行串行化后进行传递，耗时较多。
 * 
 * @author ken 2017-5-31 上午11:49:27
 */
class StudentD implements Serializable {

	private static final long serialVersionUID = 1L;
	String name;
	int age;

	StudentD(String name, int age) {
		this.name = name;
		this.age = age;
	}

}

class TeacherD implements Serializable {
	private static final long serialVersionUID = 1L;
	String name;
	int age;
	StudentD student;

	TeacherD(String name, int age, StudentD student) {
		this.name = name;
		this.age = age;
		this.student = student;
	}

	public Object deepClone() throws IOException, OptionalDataException,
			ClassNotFoundException {
		// 将对象写到流里
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(this);
		// 从流里读出来
		ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (oi.readObject());
	}
}

public class DeepCopyForSerializable {
	public static void main(String[] args) {
		StudentD student =new StudentD("小明",10);
		TeacherD t=new TeacherD("张老师",30,student);
		try {
			TeacherD t2 =(TeacherD) t.deepClone();
			t2.student.age=21;
			t2.student.name="test";
			
			t2.age=32;
			t2.name="李老师";
			
			System.out.println("学生名称:"+t.student.name+"，年龄"+t.student.age);
		}catch(Exception e){
			for (StackTraceElement ele : e.getStackTrace()) {
				System.out.println(ele);
			}
		}
	}
}