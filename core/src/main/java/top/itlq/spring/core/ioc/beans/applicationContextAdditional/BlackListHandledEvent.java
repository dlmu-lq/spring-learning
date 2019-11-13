package top.itlq.spring.core.ioc.beans.applicationContextAdditional;

import org.springframework.context.ApplicationEvent;

public class BlackListHandledEvent extends ApplicationEvent {

    private String address;
    private String content;

    @Override
    public String toString() {
        return "BlackListHandledEvent{" +
                "address='" + address + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public BlackListHandledEvent(Object source, String address, String content) {
        super(source);
        this.address = address;
        this.content = content;
    }
}
