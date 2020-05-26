package cn.thinking.list;

import java.util.NoSuchElementException;

/**
 * ListIterator �ӿ�
 * 
 * @author ken
 * @param <E>
 * 2017-4-1 ����10:20:28
 */
public interface ListIteratorMe<E> extends IteratorMe<E> {
	// Query Operations

	/**
	 * Returns true if this list iterator has more elements when
	 * traversing the list in the forward direction. (In other words, returns
	 * true if next would return an element rather than
	 * throwing an exception.)
	 * 
	 * @return true if the list iterator has more elements when
	 *         traversing the list in the forward direction.
	 */
	boolean hasNext();

	/**
	 * Returns the next element in the list. This method may be called
	 * repeatedly to iterate through the list, or intermixed with calls to
	 * previous to go back and forth. (Note that alternating calls to
	 * next and previous will return the same element
	 * repeatedly.)
	 * 
	 * @return the next element in the list.
	 * @exception NoSuchElementException
	 *                if the iteration has no next element.
	 */
	E next();

	/**
	 * Returns true if this list iterator has more elements when
	 * traversing the list in the reverse direction. (In other words, returns
	 * true if previous would return an element rather than
	 * throwing an exception.)
	 * 
	 * @return true if the list iterator has more elements when
	 *         traversing the list in the reverse direction.
	 */
	boolean hasPrevious();

	/**
	 * Returns the previous element in the list. This method may be called
	 * repeatedly to iterate through the list backwards, or intermixed with
	 * calls to next to go back and forth. (Note that alternating calls
	 * to next and previous will return the same element
	 * repeatedly.)
	 * 
	 * @return the previous element in the list.
	 * 
	 * @exception NoSuchElementException
	 *                if the iteration has no previous element.
	 */
	E previous();

	/**
	 * Returns the index of the element that would be returned by a subsequent
	 * call to next. (Returns list size if the list iterator is at the
	 * end of the list.)
	 * 
	 * @return the index of the element that would be returned by a subsequent
	 *         call to next, or list size if list iterator is at end of
	 *         list.
	 */
	int nextIndex();

	/**
	 * Returns the index of the element that would be returned by a subsequent
	 * call to previous. (Returns -1 if the list iterator is at the
	 * beginning of the list.)
	 * 
	 * @return the index of the element that would be returned by a subsequent
	 *         call to previous, or -1 if list iterator is at beginning
	 *         of list.
	 */
	int previousIndex();

	// Modification Operations

	/**
	 * Removes from the list the last element that was returned by next
	 * or previous (optional operation). This call can only be made
	 * once per call to next or previous. It can be made only
	 * if ListIterator.add has not been called after the last call to
	 * next or previous.
	 * 
	 * @exception UnsupportedOperationException
	 *                if the remove operation is not supported by this
	 *                list iterator.
	 * @exception IllegalStateException
	 *                neither next nor previous have been
	 *                called, or remove or add have been
	 *                called after the last call to next or
	 *                previous.
	 */
	void remove();

	/**
	 * Replaces the last element returned by next or previous
	 * with the specified element (optional operation). This call can be made
	 * only if neither ListIterator.remove nor
	 * ListIterator.add have been called after the last call to
	 * next or previous.
	 * 
	 * @param e
	 *            the element with which to replace the last element returned by
	 *            next or previous.
	 * @exception UnsupportedOperationException
	 *                if the set operation is not supported by this
	 *                list iterator.
	 * @exception ClassCastException
	 *                if the class of the specified element prevents it from
	 *                being added to this list.
	 * @exception IllegalArgumentException
	 *                if some aspect of the specified element prevents it from
	 *                being added to this list.
	 * @exception IllegalStateException
	 *                if neither next nor previous have been
	 *                called, or remove or add have been
	 *                called after the last call to next or
	 *                previous.
	 */
	void set(E e);

	/**
	 * Inserts the specified element into the list (optional operation). The
	 * element is inserted immediately before the next element that would be
	 * returned by next, if any, and after the next element that would
	 * be returned by previous, if any. (If the list contains no
	 * elements, the new element becomes the sole element on the list.) The new
	 * element is inserted before the implicit cursor: a subsequent call to
	 * next would be unaffected, and a subsequent call to
	 * previous would return the new element. (This call increases by
	 * one the value that would be returned by a call to nextIndex or
	 * previousIndex.)
	 * 
	 * @param e
	 *            the element to insert.
	 * @exception UnsupportedOperationException
	 *                if the add method is not supported by this list
	 *                iterator.
	 * 
	 * @exception ClassCastException
	 *                if the class of the specified element prevents it from
	 *                being added to this list.
	 * 
	 * @exception IllegalArgumentException
	 *                if some aspect of this element prevents it from being
	 *                added to this list.
	 */
	void add(E e);
}
