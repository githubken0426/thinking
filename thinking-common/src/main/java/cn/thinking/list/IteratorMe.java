package cn.thinking.list;

import java.util.NoSuchElementException;
/**
 * Iterator�ӿ�
 * 
 * @author ken
 * @param <E>
 * 2017-4-1 ����10:20:38
 */
public interface IteratorMe<E> {
	/**
	 * Returns true if the iteration has more elements. (In other words, returns
	 * true if next would return an element rather than throwing an exception.)
	 * 
	 * @return true if the iterator has more elements.
	 */
	boolean hasNext();

	/**
	 * Returns the next element in the iteration.
	 * 
	 * @return the next element in the iteration.
	 * @exception NoSuchElementException
	 *                iteration has no more elements.
	 */
	E next();

	/**
	 * 
	 * Removes from the underlying collection the last element returned by the
	 * iterator (optional operation). 
	 * This method can be called only once per call to next. 
	 * The behavior of an iterator is unspecified if the
	 * underlying collection is modified while the iteration is in progress in
	 * any way other than by calling this method.
	 * 
	 * @exception UnsupportedOperationException
	 *             if the remove operation is not supported by this Iterator.
	 * @exception IllegalStateException
	 *                if the next method has not yet been called, or
	 *                the remove method has already been called after
	 *                the last call to the next method.
	 */
	void remove();
}
