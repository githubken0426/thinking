package cn.thinking.design.pattern.chapter07_Adapter.theAnswer;

public class Test {
    public static void main(String[] args) {
        Iterator<String> it = new IteratorAdapter<>(new Enumeration<String>() {
            @Override
            public boolean hasMoreElement() {
                System.out.println("Enumeration hasMoreElement!");
                return false;
            }

            @Override
            public String nextElement() {
                System.out.println("Enumeration nextElement!");
                return null;
            }
        });
        it.hasNext();
        it.next();
    }
}
