package cn.thinking.map.lang;


import cn.thinking.list.IteratorMe;

/**
 * @author ken
 * @param <T>
 * 2017-4-1 09:07:56
 */
public interface IterableMe<T> {
	/**
     * Returns an iterator over a set of elements of type T.
     * 
     * @return an Iterator.
     */
	public IteratorMe<T> iterator();
}
