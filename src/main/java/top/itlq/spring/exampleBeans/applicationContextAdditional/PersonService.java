package top.itlq.spring.exampleBeans.applicationContextAdditional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class PersonService{

    private ApplicationEventPublisher applicationEventPublisher;

    public PersonService(ApplicationEventPublisher applicationEventPublisher){
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void createPerson(){
        applicationEventPublisher.publishEvent(new EntityCreatedEvent<>(new Person()));
        applicationEventPublisher.publishEvent(new EntityCreatedEvent<>(new Object()));
    }
}
