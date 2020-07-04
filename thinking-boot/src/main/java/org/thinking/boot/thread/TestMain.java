package org.thinking.boot.thread;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.thinking.boot.scope.ScopeConfig;
import org.thinking.boot.scope.ScopeService.PrototypeBean;
import org.thinking.boot.scope.ScopeService.SingletonBean;

public class TestMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExecuteConfig.class);
        AsyncTaskService service = context.getBean(AsyncTaskService.class);
        for (int i = 0; i < 10; i++) {
            service.executeAsyncTask(i);
            service.executeAsyncTaskPlus(i);
        }
        context.close();
    }
}
