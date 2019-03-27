package top.itlq.spring.exampleBeans.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public class Talk {
    private Event event;

    public Event getEvent() {
        return event;
    }

    @Autowired
    public void setEvent(Event event) {
        this.event = event;
    }
}
