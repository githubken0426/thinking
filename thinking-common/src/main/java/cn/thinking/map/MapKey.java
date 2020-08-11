package cn.thinking.map;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.regex.Pattern;

public class MapKey {
	/**
	 * 1.2 index的计算原则 Entry[]数组的长度在初始化的时候会被指定，假定这个值为length。那index的值就从 0
	 * ～length-1。 所以index需要尽可能的平衡，也就是分布均匀，不能某些位置上存储特别多的数据，某些位置上又特别少。
	 * 前面我们说过，通过hash值来计算index，那使用什么办法可以满足：性能高效均匀分析
	 * 
	 * 1.3 index的计算方法， 1.3.1 取模运算 hash值为int，index需要映射到0 ～
	 * length-1，最直观的使用取模运算，也就是：index = hash值 % length 这个时候index的值的范围就是 0 ～
	 * length-1，但是，Java 官方没有采用这个办法，因为这种效率不是最高的。
	 * 
	 * 1.3.2 位运算（为什么长度一定是2的n次方） 为了解决取模效率的问题，Java官方采用了位运算的方法（"与"操作）： index = hash
	 * & （length -1） 这个时候，如果要index的值的范围也是 0 ～
	 * length-1，需要一个前置条件：length的长度必须是2的n次幂。
	 */

	/**
	 * HashMap常见面试题：
	 * 
	 * 1、HashMap的底层数据结构？
	   *   数组和链表.数组的特点是：寻址容易，插入和删除困难；而链表的特点是：寻址困难，插入和删除容易
	 * 
	 * 2、HashMap的存取原理？
	 * key的hash算法，计算出key的hashcode,然后根据table的容量计算出需要插入数组的下标。
	   *   如果hashcode冲突，计算出的下标相同，数组就会以链表形式存储，如果链表长度大于8且容量大于64，会以红黑树来存储。然后调用put方法，如果table的threshold=加载因子*容量,就会触发table的扩容。
	 * 
	 * 3、Java7和Java8的区别？
	 *  1.7是数组+链表；1.8数组+链表+红黑树
	 * 
	 * 4、为啥会线程不安全？
	 * 	1.7 扩容的时候会调用 resize() 方法，就是这里的并发操作容易在一个桶上形成环形链表；这样当获取一个不存在的 key 时，计算出的 index 正好是环形链表的下标就会出现死循环.
	 *  1.8 putVal时，(p = tab[i = (n - 1) & hash]) == null,如果没有hash碰撞则会直接插入元素，刚好这两条不同的数据hash值一样，并且该位置数据为null,线程AB同事执行，会覆盖put的值。
	 *  
	 * 5、有什么线程安全的类代替么?
	 * 	1，HashTable,2,Collections.synchronizedMap(map),
	 *  3,CopyOnWriteMap,
	 *  3.1.内存占用 因为CopyOnWrite的写时复制机制，所有在进行写操作的时候，内存里会同时驻扎两个对象的内存，旧的对象和新的对象。如果这些对象占用的内存比较大，就有可能造成频繁的yong GC和full GC。
	   *    解决：可以通过压缩容器中的元素的方法来减少大对象的消耗，如元素全是10进制的数字，可以考虑将其压缩成32进制或者64进制.
	 *  3.2.数据一致性CopyOnWrite容器只能保证数据的最终一致性，不能保证数据的实时一致性（因为在对新容器进行写操作时，其它线程可能也在操作原容器）。所有如果你希望写入的数据，马上能读到，CopyOnWrite可能无法满足要求。
	 *  
	 * 	4,ConcurrentHashMap.采用锁分段技术，减小锁的粒度，效率高ConcurrentHashMap中是一次锁住一个桶。ConcurrentHashMap默认将hash表分为16个桶，诸如get,put,remove等常用操作只锁当前需要用到的桶。
	 * 	    这样，原来只能一个线程进入，现在却能同时有16个写线程执行，并发性能的提升是显而易见的。上面说到的16个线程指的是写线程，而读操作大部分时候都不需要用到锁。只有在size等操作时才需要锁住整个hash表。
	 * 
	 * 6、默认初始化大小是多少？为啥是这么多？为啥大小都是2的幂？ 
	 * 6.1， 
	 * 	1 << 4，1左移4位，aka16.必须是2的幂次方。
	 * 6.2，
	 * key的hash方法： static final int hash(Object key) { int h; return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16); }
	 * 	之所以要进行再hash,是因为在算数组（即table/Node）索引时,当数组长度较少,参与位与运算的位数在低位并且较少,这样相同的hashcode值算出相同index的概率较大.所以通过再hash,让更多位数间接参与index计算.
	 * 
	 * 6.3
	 * table index计算方法: table = tab[i = (n - 1) & hash],之所以是2的幂，为了提高效率，为了得到一个均匀分布的数组下标.
	 * 1,为了提高效率，用位与（&）运算代替取模（%）运算[hash%(table.length)]
	 *  15-1:0000 0000 0000 0000 0000 0000 0000 1110
	 * 2,若不为2的幂次,则(n - 1)后低位必存在0,这样位与运算后该位结果为0,那么在该位上为1（即奇数索引）[0011（3），0101（5），0111（7），1001（9），1101（13）]的索引永远用不到,数组存在浪费，
	 * 	数组可以使用的位置比数组长度小了很多，这意味着进一步增加了碰撞的几率，减慢了查询的效率。
	 * 3,扩容时,方便定位。
	 * 
	 * 7、HashMap的扩容方式？负载因子是多少？为什是这么多？
	   *   当threshold = loadFactor * capacity开始resize, 扩容一倍：newCap = oldCap << 1.
	   *   默认的负载因子(0.75)在时间和空间成本之间提供了很好的权衡。
	   *   负载因子太小了浪费空间并且会发生更多次数的resize;
	   *   负载因子更高的值减少了空间开销，但增加hash碰撞的几率，也就增加了查找成本(反映在HashMap类的大多数操作中，包括get和put);
	 * 0.75和任何2的幂乘积结果都是整数.（和泊松分布没有关系，能够影响泊松分布）.
	 * 
	 * 8、HashMap的主要参数都有哪些？
	 * 
	 * HashMap是怎么处理hash碰撞的？
	 * 
	 * hash的计算规则？
	 */
	private static final String REG = "[0-9]+";

	private String key;

	public MapKey(String key) {
		this.key = key;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		MapKey mapKey = (MapKey) o;

		return !(key != null ? (!key.equals(mapKey.key)) : (mapKey.key != null));

	}

	@Override
	public int hashCode() {
		if (key == null)
			return 0;
		Pattern pattern = Pattern.compile(REG);
		if (pattern.matcher(key).matches())
			return 100;
		else
			return 101;
	}

	@Override
	public String toString() {
		return key;
	}

	static Class<?> comparableClassFor(Object x) {
		if (x instanceof Comparable) {
			Class<?> c = x.getClass();
			Type[] ts, as;
			ParameterizedType p;
			if (c == String.class) // bypass checks
				return c;
			if ((ts = c.getGenericInterfaces()) == null) {
				return null;
			}
			for (Type t : ts) { // type arg is c
				if ((t instanceof ParameterizedType) && ((p = (ParameterizedType) t).getRawType() == Comparable.class)
						&& (as = p.getActualTypeArguments()) != null && as.length == 1 && as[0] == c)
					return c;
			}
		}
		return null;
	}
	//	返回这个数的二进制从最高位算起连续的“0”的总数量。
	public static int numberOfLeadingZeros(int i) {
		// HD, Count leading 0's
		if (i <= 0)
			return i == 0 ? 32 : 0;
		System.out.println(1 << 16);
		int n = 31;
		if (i >= 1 << 16) {
			n -= 16;
			i >>>= 16;
		}
		if (i >= 1 << 8) {
			n -= 8;
			i >>>= 8;
		}
		if (i >= 1 << 4) {
			n -= 4;
			i >>>= 4;
		}
		if (i >= 1 << 2) {
			n -= 2;
			i >>>= 2;
		}
		return n - (i >>> 1);
	}
}
