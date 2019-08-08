package top.itlq.spring.exampleBeans.applicationContextAdditional;

import org.springframework.context.ApplicationEvent;

public class BlackListEvent extends ApplicationEvent {

    private String address;

    public String getAddress() {
        return address;
    }

    public String getContent() {
        return content;
    }

    private String content;

    @Override
    public String toString() {
        return "BlackListEvent{" +
                "address='" + address + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public BlackListEvent(Object source, String address, String content) {
        super(source);
        this.address = address;
        this.content = content;
    }
}
