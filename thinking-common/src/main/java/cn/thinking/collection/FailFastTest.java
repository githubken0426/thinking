package cn.thinking.collection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * collection fail-fast����
 * 
 * @author ken 2017-5-12 ����02:31:20
 */
public class FailFastTest {
	public static void main(String[] args) {
		String [] ele=new String[]{"a", "b", "c","d"};
		final List<String> list = new MyArrayListNoFailFast<String>(ele);
		new Thread(new Runnable() {
			@Override
			public void run() {
				Iterator<String> it=list.iterator();
				while(it.hasNext()){
					System.out.println("Iterator:"+it.next());
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				ListIterator<String> its=list.listIterator();
				//�����и��ļ���Ԫ�ز���ʱ�򣬴���fail-fast:java.util.ConcurrentModificationException
//				it.add("efo");
				while(its.hasPrevious()){
					System.out.println("ListIterator:"+its.previous());
				}
			}
		}).start();
	}
}
class MyArrayListNoFailFast<E> extends ArrayList<E> implements List<E>, Serializable{
	
	private static final long serialVersionUID = 1L;
	public final Object[] obj;
	public MyArrayListNoFailFast(Object[] obj){
		this.obj=obj;
	}
	
	public Iterator<E> iterator(){
		return new MyIterator<E>(obj,0);
	}
	public ListIterator<E> listIterator(){
		return new MyIterator<E>(obj,0);
	}
	public class MyIterator<E> implements ListIterator<E>{
		private final Object[] snapshot;
		private int cursor;

		public MyIterator(Object[] elements,int initialCursor){
			cursor=initialCursor;
			snapshot=elements;
		}
		@Override
		public void add(E e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean hasNext() {
			return cursor<snapshot.length;
		}

		@Override
		public boolean hasPrevious() {
			return cursor>0;
		}

		@Override
		public E next() {
			if (! hasNext())
				 throw new NoSuchElementException();
			return (E) snapshot[cursor++];

		}

		@Override
		public int nextIndex() {
			return cursor;
		}

		@Override
		public E previous() {
			if (! hasPrevious())
				 throw new NoSuchElementException();
			return (E) snapshot[cursor--];
		}

		@Override
		public int previousIndex() {
			return cursor-1;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(E e) {
			throw new UnsupportedOperationException();
		}
	}
}
