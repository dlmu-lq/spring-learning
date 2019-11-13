package top.itlq.spring.core.ioc.beans.applicationContextAdditional;

import org.springframework.context.event.EventListener;

public class EntityCreatedNotifier {
    @EventListener
    public void entityCreated(EntityCreatedEvent entityCreatedEvent){
        System.out.println("entityCreatedNotifier got message with entityCreated.");
    }
    @EventListener
    public void personCreated(EntityCreatedEvent<Person> entityCreatedEvent){
        System.out.println("entityCreatedNotifier got message with personCreated.");
    }
}
