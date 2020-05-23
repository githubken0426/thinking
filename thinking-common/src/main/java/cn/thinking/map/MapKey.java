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
	 * 1、HashMap的底层数据结构？ 数组和链表.数组的特点是：寻址容易，插入和删除困难；而链表的特点是：寻址困难，插入和删除容易
	 * 
	 * 2、HashMap的存取原理？
	 * 
	 * 3、Java7和Java8的区别？
	 * 
	 * 4、为啥会线程不安全？
	 * 
	 * 5、有什么线程安全的类代替么?
	 * 
	 * 6、默认初始化大小是多少？为啥是这么多？为啥大小都是2的幂？ 6.1， 1 << 4，1左移4位，aka16.必须是2的幂次方。 6.2，
	 * key的hash方法： static final int hash(Object key) { int h; return (key ==
	 * null) ? 0 : (h = key.hashCode()) ^ (h >>> 16); }
	 * 之所以要进行再hash,是因为在算数组索引时,当数组长度较少,参与位与运算的位数在低位并且较少,这样相同的hashcode值算出相同index的概率较大.所以通过再hash,让更多位数间接参与index计算.
	 * table index计算方法: table = tab[i = (n - 1) & hash]
	 * 之所以是2的幂，为了提高效率，为了得到一个均匀分布的数组下标.
	 * 1,为了提高效率，用位与（&）运算代替取模（%）运算（hash%(table.length)） 2,若不为2的幂次,则(n -
	 * 1)后低位必存在0,这样位与运算后该位结果为0,那么在该位上为1的索引永远用不到,数组存在浪费，数组可以使用的位置比数组长度小了很多，这意味着进一步增加了碰撞的几率，减慢了查询的效率。
	 * 3,扩容时,方便定位。
	 * 
	 * HashMap的扩容方式？负载因子是多少？为什是这么多？
	 * 
	 * HashMap的主要参数都有哪些？
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
	//返回这个数的二进制从最高位算起连续的“0”的总数量。
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
