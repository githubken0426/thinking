package org.thinking.boot.thread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service()
public class AsyncTaskService {

    @Async
    public void executeAsyncTask(Integer i) {
        System.out.println("executeAsyncTask: " + i);
    }

    @Async
    public void executeAsyncTaskPlus(Integer i) {
        System.out.println("executeAsyncTaskPlus: " + (i + 1));
    }
}
