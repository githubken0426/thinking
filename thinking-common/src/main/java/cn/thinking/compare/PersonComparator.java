package cn.thinking.compare;

import entity.serializable.Person;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 外部比较器
 */
public class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        int temp = o1.getAge() - o2.getAge();
        int str = o1.getName().compareTo(o2.getName());
        return temp == 0 ? str : temp;
    }

    public static void main(String[] args) {
        Person person[] = new Person[]{
                new Person(10, "giu"), new Person(20, "alo"),
                new Person(23, "bco"), new Person(21, "bko")
        };
        Arrays.sort(person, new PersonComparator());
        for (int i = 0; i < person.length; i++) {
            System.out.println(person[i].toString());
        }
    }
}
