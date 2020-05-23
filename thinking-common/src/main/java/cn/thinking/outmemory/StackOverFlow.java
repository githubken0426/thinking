package cn.thinking.outmemory;

public class StackOverFlow {
    private int stackLength = 0;

    public void stackOverFlow() {
        ++stackLength;
        stackOverFlow();
    }

    public static void main(String[] args) {
        StackOverFlow t = new StackOverFlow();
        try {
            t.stackOverFlow();
        } catch (Throwable e) {
            System.out.println("stack length:" + t.stackLength);
            e.printStackTrace();
        }
    }
}
