package cn.thinking.concurrent;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MyThreadMain {
    public static void main(String[] args) {
        ExecutorService exe = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                UncaughtExceptionHandler uncaughtExceptionHandler = new UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        System.out.println("UncaughtExceptionHandler Throwable:[" + e
                                + "],t.getName():[" + t.getName() + "]");
                    }
                };
                thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
                return thread;
            }
        });
        exe.execute(new MyThread());
    }
}

class MyThread implements Runnable {

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
