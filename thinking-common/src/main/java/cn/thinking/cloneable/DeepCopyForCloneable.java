package cn.thinking.cloneable;

/**
 * 深度copy
 * @author ken
 */
class StudentD2 implements Cloneable {

	String name;
	int age;

	StudentD2(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}

class TeacherD2 implements Cloneable {
	String name;
	int age;
	StudentD2 student;

	TeacherD2(String name, int age, StudentD2 student) {
		this.name = name;
		this.age = age;
		this.student = student;
	}

	public Object clone() throws CloneNotSupportedException {
		TeacherD2 teacher = (TeacherD2) super.clone();
		teacher.student = (StudentD2) student.clone();
		return teacher;
	}
}

public class DeepCopyForCloneable {
	public static void main(String[] args) {
		StudentD2 student = new StudentD2("小明", 10);
		TeacherD2 t = new TeacherD2("张老师", 30, student);
		try {
			TeacherD2 t2 = (TeacherD2) t.clone();
			t2.student.age = 21;
			t2.student.name = "test";

			t2.age = 32;
			t2.name = "李老师";

			System.out.println("学生名称:" + t.student.name + "，年龄" + t.student.age);
		} catch (Exception e) {
			e.printStackTrace();
			for (StackTraceElement ele : e.getStackTrace()) {
				System.out.println(ele);
			}
		}
	}
}