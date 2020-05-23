package cn.thinking.design.pattern.chapter01_Strategy.theProject;

import entity.project.Application;
import entity.serializable.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class HandlerContext {

    private Map<EventEnum, HandlerStrategyService> strategyServiceMap;

    @Autowired
    private List<HandlerStrategyService> strategyServices;

    private HandlerStrategyService defaultStrategy;

    @PostConstruct
    private void init() {
        strategyServiceMap = new HashMap<>();
        strategyServices.forEach(strategy -> {
            List<EventEnum> supportedEvents = strategy.getEventEnum();
            // if strategy supported event is null or empty, set default strategy
            if (CollectionUtils.isEmpty(supportedEvents)) {
                defaultStrategy = strategy;
                return;
            }
            supportedEvents.forEach(l -> strategyServiceMap.put(l, strategy));
        });
    }

    public void handle(EventEnum eventEnum, Application application, Person person) {
        // all events that don't have a matching strategy service will execute default one
        if (!strategyServiceMap.containsKey(eventEnum)) {
            defaultStrategy.handleData(application, person);
            return;
        }
        strategyServiceMap.get(eventEnum).handleData(application, person);
    }
}
