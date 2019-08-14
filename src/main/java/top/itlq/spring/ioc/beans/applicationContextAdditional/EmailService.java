package top.itlq.spring.ioc.beans.applicationContextAdditional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import java.util.List;

public class EmailService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;
    private List<String> blackList;

    public EmailService(List<String> blackList){
        this.blackList = blackList;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void sendEmail(String address, String context){
        if(blackList.contains(address)){
            applicationEventPublisher.publishEvent(new BlackListEvent(this, address, context));
            return;
        }
        System.out.println("sending email to " + address);
    }

    public void sendObjectEvent(){
        applicationEventPublisher.publishEvent(new Person());
    }
}
