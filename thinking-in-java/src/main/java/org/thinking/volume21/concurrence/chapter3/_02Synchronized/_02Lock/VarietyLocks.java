package org.thinking.volume21.concurrence.chapter3._02Synchronized._02Lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 1.1 	乐观锁/悲观锁 ： 乐观锁与悲观锁并不是特指某两种类型的锁，是人们定义出来的概念或思想，主要是指看待并发同步的角度。
 * 
 * 乐观锁：顾名思义，就是很乐观，每次去拿数据的时候都认为别人不会修改，所以不会上锁，但是在更新的时候会判断一下在此期间别人有没有去更新这个数据，可以使用版本号等机制。
 * 在Java中java.util.concurrent.atomic包下面的原子变量类就是使用了乐观锁的一种实现方式CAS(Compare and Swap比较并交换)。
 * 乐观锁适用于多读的应用类型，这样可以提高吞吐量。
 * 
 * 悲观锁：总是假设最坏的情况，每次去拿数据的时候都认为别人会修改，所以每次在拿数据的时候都会上锁，这样别人想拿这个数据就会阻塞直到它拿到锁。
 * 比如Java里面的同步原语synchronized关键字的实现就是悲观锁。悲观锁适合写操作非常多的场景。
 * 
 * 1.2 	独享锁/共享锁:独享锁是指该锁一次只能被一个线程所持有。共享锁是指该锁可被多个线程所持有。 
 * 对于Java ReentrantLock而言，其是独享锁。但是对于Lock的另一个实现类ReentrantReadWriteLock，其读锁是共享锁，其写锁是独享锁。
 * 读锁的共享锁可保证并发读是非常高效的；读写，写读，写写的过程是互斥的。 独享锁与共享锁也是通过AQS来实现的，通过实现不同的方法，来实现独享或者共享。
 * 对于Synchronized而言，当然是独享锁。
 * 
 * 1.3 	互斥锁/读写锁 :独享锁/共享锁就是一种广义的说法，互斥锁/读写锁就是具体的实现。 
 * 互斥锁在Java中的具体实现就是ReentrantLock。
 * 读写锁在Java中的具体实现就是ReentrantReadWriteLock。
 * 
 * 1.4 	可重入锁 :可重入锁又名递归锁，是指在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁。
 * 
 * 1.5 	公平锁/非公平锁:公平锁是指多个线程按照申请锁的顺序来获取锁。
 * 非公平锁是指多个线程获取锁的顺序并不是按照申请锁的顺序，有可能后申请的线程比先申请的线程优先获取锁。有可能，会造成优先级反转或者饥饿现象。 
 * 对于Java ReetrantLock而言，通过构造函数指定该锁是否是公平锁，默认是非公平锁。非公平锁的优点在于吞吐量比公平锁大。
 * 对于Synchronized而言，也是一种非公平锁。由于其并不像ReentrantLock是通过AQS的来实现线程调度，所以并没有任何办法使其变成公平锁。
 * 
 * 1.6 	分段锁:是一种锁的设计，并不是具体的一种锁。 
 * 对于ConcurrentHashMap而言，其并发的实现就是通过分段锁的形式来实现高效的并发操作。
 * ConcurrentHashMap中的分段锁称为Segment，它即类似于HashMap（JDK7和JDK8中HashMap的实现）的结构，即内部拥有一个Entry数组，数组中的每个元素又是一个链表；
 * 同时又是一个ReentrantLock（Segment继承了ReentrantLock）。
 * 当需要put元素的时候，并不是对整个hashmap进行加锁，而是先通过hashcode来知道他要放在哪一个分段中，然后对这个分段进行加锁;
 * 所以当多线程put的时候，只要不是放在一个分段中，就实现了真正的并行的插入。但是，在统计size的时候，可就是获取hashmap全局信息的时候，就需要获取所有的分段锁才能统计。
 * 分段锁的设计目的是细化锁的粒度，当操作不需要更新整个数组的时候，就仅仅针对数组中的一项进行加锁操作。
 * 
 * 1.7 	偏向锁（Biased）/轻量级锁（Lightweight）/重量级锁 （Heavyweight ）:这三种锁是指锁的状态，在Java 5通过引入锁升级的机制来实现高效Synchronized。
 * 这三种锁的状态是通过对象监视器在对象头中的字段来表明的。
 * 偏向锁是指一段同步代码一直被一个线程所访问，那么该线程会自动获取锁。降低获取锁的代价。
 * 轻量级锁是指当锁是偏向锁的时候，被另一个线程所访问，偏向锁就会升级为轻量级锁，其他线程会通过自旋的形式尝试获取锁，不会阻塞，提高性能。
 * 重量级锁是指当锁为轻量级锁的时候，另一个线程虽然是自旋，但自旋不会一直持续下去，当自旋一定次数的时候，还没有获取到锁，就会进入阻塞，该锁膨胀为重量级锁。重量级锁会让他申请的线程进入阻塞，性能降低。
 * 
 * 1.8 	自旋锁
 * 在Java中，自旋锁是指尝试获取锁的线程不会立即阻塞，而是采用循环的方式去尝试获取锁，这样的好处是减少线程上下文切换的消耗，缺点是循环会消耗CPU。
 * 
 * @author kun.f.wang
 *
 */
public class VarietyLocks {
	AbstractQueuedSynchronizer aqs;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public static void main(String[] args) {
		String str="\"te\"st\"";
		System.out.println(str);
		System.out.println(replaceStr(str));
		System.out.println("\"te\"st");
		System.out.println(replaceStr("\"te\"st"));
		System.out.println("\"\"\"te\"st\"\"\"");
		System.out.println(replaceStr("\"\"\"te\"st\"\"\""));
	}

	private static String replaceStr(String str){
        Pattern pattern = Pattern.compile("^\"+|\"+$");
        Matcher matcher = pattern.matcher(str);
        return matcher.replaceAll("");
    }
}
