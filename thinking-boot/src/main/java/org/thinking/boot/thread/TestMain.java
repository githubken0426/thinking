package org.thinking.boot.thread;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExecuteConfig.class);
        AsyncTaskService service = context.getBean(AsyncTaskService.class);
        for (int i = 0; i < 10; i++) {
            service.executeAsyncTask(i);
            service.executeAsyncTaskPlus(i);
        }
        context.getBean(ScheduleTaskService.class);
        context.close();
    }
}
