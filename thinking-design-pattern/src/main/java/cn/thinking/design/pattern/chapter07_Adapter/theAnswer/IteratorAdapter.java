package cn.thinking.design.pattern.chapter07_Adapter.theAnswer;

public class IteratorAdapter<E> implements Iterator<E> {
    Enumeration<E> enumeration;

    public IteratorAdapter(Enumeration<E> enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElement();
    }

    @Override
    public E next() {
        return enumeration.nextElement();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
