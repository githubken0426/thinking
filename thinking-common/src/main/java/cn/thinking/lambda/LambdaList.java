package cn.thinking.lambda;

import entity.serializable.Person;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: kun.f.wang
 * @Date: 2/11/2019 17:38
 * @Description:
 */
public class LambdaList {
    static List<Person> proList = new ArrayList<>();

    /**
     * 通过stream方法创建Stream，然后再通过filter方法对源数据进行过滤，最后通过foeEach方法进行迭代。
     * 在聚合操作中，与Lambda表达式一起使用，显得代码更加的简洁。这里值得注意的是，我们首先是stream方法的调用，
     * 其与iterator作用一样的作用一样，该方法不是返回一个控制迭代的 Iterator 对象，而是返回内部迭代中的相应接口：
     * Stream，其一系列的操作都是在操作Stream,直到foreach时才会操作结果，这种迭代方式称为内部迭代。
     * 外部迭代和内部迭代(聚合操作)都是对集合的迭代，但是在机制上还是有一定的差异：
     *
     * 迭代器提供next()、hasNext()等方法，开发者可以自行控制对元素的处理，以及处理方式，但是只能顺序处理；
     * stream()方法返回的数据集无next()等方法，开发者无法控制对元素的迭代，迭代方式是系统内部实现的，
     * 同时系统内的迭代也不一定是顺序的，还可以并行，如parallelStream()方法。
     * 并行的方式在一些情况下，可以大幅提升处理的效率。
     */
    static {
        Person p1 = new Person();
        p1.setAge(10);
        p1.setName("张三");
        proList.add(p1);
        Person p2 = new Person();
        p2.setAge(25);
        p2.setName("王强");
        proList.add(p2);

        Person p3 = new Person();
        p3.setAge(30);
        p3.setName("P30");
        proList.add(p3);
    }

    public static void main(String[] args) {
        test(proList);
    }

    public static void test(List<Person> proList) {
        if (CollectionUtils.isEmpty(proList))
            return;
        proList.stream()
                .filter((p) -> p.getAge() > 10)
                .limit(2)
                .forEach(System.out::println);
    }
}
