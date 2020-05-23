package cn.thinking.design.pattern.chapter01_Strategy.theProject;

import entity.project.Application;
import entity.serializable.Person;

public abstract class AbstractHandlerStrategy implements HandlerStrategyService {

    @Override
    public void handleData(Application application, Person person) {
        doHandle(application, person);
    }

    protected abstract void doHandle(Application application, Person person);
}
