package cn.thinking.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * FutureTask是Future接口的一个唯一实现类。
 * public class FutureTask<V> implements RunnableFuture<V>
 *
 * public interface RunnableFuture<V> extends Runnable, Future<V> {
 *     void run();
 * }
 *
 * @Auther: kun.f.wang
 * @Date: 2/14/2019 09:28
 * @Description:
 */
public interface MyFuture<V> {
    /**
     * cancel方法用来取消任务.取消成功,返回true;取消失败,返回false。
     * 参数mayInterruptIfRunning表示是否允许取消正在执行却没有执行完毕的任务，如果设置true，则表示可以取消正在执行过程中的任务。
     * 如果任务已经完成，则无论mayInterruptIfRunning为true还是false，此方法肯定返回false；
     * 即如果取消已经完成的任务会返回false；
     * 如果任务正在执行，若mayInterruptIfRunning设置为true，则返回true；若mayInterruptIfRunning设置为false，则返回false；
     * 如果任务还没有执行，则无论mayInterruptIfRunning为true还是false，肯定返回true。
     *
     * @param mayInterruptIfRunning
     * @return
     */
    boolean cancel(boolean mayInterruptIfRunning);

    boolean isCancelled();

    boolean isDone();

    /**
     * 获取执行结果，此方法会生阻塞，直到任务执行完毕才返回
     *
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    V get() throws InterruptedException, ExecutionException;

    /**
     * 如果在指定时间内，还没获取到结果，get方法会throw new TimeoutException();
     *
     * @param timeout
     * @param unit
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;
}
