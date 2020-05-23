package cn.thinking.compare;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 内部比较器
 */
public class PersonComparable implements Comparable<PersonComparable> {
	private int age;
	private String name;

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

	public PersonComparable(int age, String name) {
		this.age = age;
		this.name = name;
	}

	/**
	 * 比较当前实例对象与对象obj
	 * 如果位于对象obj之前，返回负值， 
	 * 如果两个对象在排序中位置相同，则返回 0 ， 
	 * 如果位于对象obj后面，则返回正值
	 */
	@Override
	public int compareTo(PersonComparable obj) {
		// int temp = age - obj.getAge();
		// return temp == 0 ? ten : temp;
		System.out.println("name:" + name + ",obj.getName:" + obj.getName());
		// 此处是调用String类的compareTo方法
		int ten = name.compareTo(obj.getName());
		System.out.println(ten);
		return ten;
	}

	public String toString() {
		return "{" + age + "--" + name + "}";
	}

	/**
	 * 使用Arrays对数组进行排序，使用Collections对结合框架容器进行排序，如ArraysList,LinkedList等
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PersonComparable[] person = { new PersonComparable(20, "Tom"), new PersonComparable(20, "Jeff"),
				new PersonComparable(30, "Mary"), new PersonComparable(20, "Ada"), new PersonComparable(40, "Walton"),
				new PersonComparable(61, "Peter"), new PersonComparable(20, "Bush") };
		System.out.println("排序前：" + Arrays.toString(person));
		Arrays.sort(person);
		// Collections.sort(list);
		System.out.println("排序后：" + Arrays.toString(person));

		System.out.println("TreeSet:");
		TreeSet<PersonComparable> set = new TreeSet<PersonComparable>();
		set.add(new PersonComparable(20, "Tom"));
		set.add(new PersonComparable(20, "Jeff"));
		set.add(new PersonComparable(30, "Mary"));
		set.add(new PersonComparable(20, "Ada"));
		set.add(new PersonComparable(40, "Walton"));
		set.add(new PersonComparable(61, "Peter"));
		set.add(new PersonComparable(20, "Bush"));
		System.out.println(set);
		System.out.println("TreeMap:");
		TreeMap<PersonComparable, String> map = new TreeMap<PersonComparable, String>();
		map.put(new PersonComparable(20, "Tom"), "Tom");
		map.put(new PersonComparable(20, "Jeff"), "Jeff");
		map.put(new PersonComparable(30, "Mary"), "Mary");
		map.put(new PersonComparable(20, "Ada"), "Ada");
		map.put(new PersonComparable(40, "Walton"), "Walton");
		map.put(new PersonComparable(61, "Peter"), "Peter");
		map.put(new PersonComparable(20, "Bush"), "Bush");
		System.out.println(map);
	}
}
