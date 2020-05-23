package cn.thinking.design.pattern.chapter01_Strategy.theProject;

import entity.project.Application;
import entity.serializable.Person;

import java.util.List;

public interface HandlerStrategyService {

    void handleData(Application application, Person person);

    List<EventEnum> getEventEnum();
}
