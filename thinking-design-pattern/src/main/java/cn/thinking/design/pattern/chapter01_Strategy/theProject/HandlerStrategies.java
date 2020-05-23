package cn.thinking.design.pattern.chapter01_Strategy.theProject;

import entity.project.Application;
import entity.serializable.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HandlerStrategies {
    private static final Logger logger = LogManager.getLogger();

    @Service
    public class StartStrategy extends AbstractHandlerStrategy {

        @Override
        public void doHandle(Application application, Person person) {
            logger.info("RED.");
        }

        @Override
        public List<EventEnum> getEventEnum() {
            List<EventEnum> list = new ArrayList<>();
            list.add(EventEnum.RED);
            return list;

        }
    }

    @Service
    public class DefaultStrategy extends AbstractHandlerStrategy {

        @Override
        public void doHandle(Application application, Person person) {
            logger.info("do nothing.");
        }

        @Override
        public List<EventEnum> getEventEnum() {
            return null;

        }
    }

}
