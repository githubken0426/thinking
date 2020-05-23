package cn.thinking.concurrent.future.part_3;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: kun.f.wang
 * @Date: 2/14/2019 09:55
 * @Description:
 */
public class MyCallable implements Callable<String> {
    private long waitTime;

    public MyCallable(int waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(waitTime);
        return Thread.currentThread().getName();
    }
}
