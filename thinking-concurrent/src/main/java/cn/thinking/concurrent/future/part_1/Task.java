package cn.thinking.concurrent.future.part_1;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: kun.f.wang
 * @Date: 2/14/2019 11:34
 * @Description:
 */
public class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        TimeUnit.SECONDS.sleep(3);
        return 200;
    }
}