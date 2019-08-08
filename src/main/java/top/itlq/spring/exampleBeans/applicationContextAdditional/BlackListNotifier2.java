package top.itlq.spring.exampleBeans.applicationContextAdditional;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

public class BlackListNotifier2 {

    /**
     * 根据类型接收事件
     * @param event
     */
    @EventListener
    @Order(2) // 默认最后触发
    public BlackListHandledEvent blackListEvent(BlackListEvent event){
        System.out.println("blackListNotifier2 got message," + event);
        return new BlackListHandledEvent(this, event.getAddress(), event.getContent());
    }
}
