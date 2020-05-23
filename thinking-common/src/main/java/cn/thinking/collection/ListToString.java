package cn.thinking.collection;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 重写List toString
 * @ClassName: ListToString
 * @Description: 
 * @author ken 
 * @date 2018年1月5日 上午10:02:02 
 * @param <E>
 */
public class ListToString<E> extends ArrayList<E> {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();//线程安全
		Iterator<E> it = iterator();
		boolean hasNext = it.hasNext();
		while (hasNext) {
			E str = it.next();
			sb.append(str);
			hasNext = it.hasNext();
			if (hasNext)
				sb.append(",");
		}
		return sb.toString();
	}
}
