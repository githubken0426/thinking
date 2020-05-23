package cn.thinking.design.pattern.chapter07_Adapter.theAnswer;

public interface Iterator<E> {
    boolean hasNext();

    E next();

    void remove();
}
