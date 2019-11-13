package top.itlq.spring.core.ioc.beans.applicationContextAdditional;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class BlackListNotifier implements ApplicationListener {

    /**
     * 根据类型接收事件
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("blackListNotifier got message," + event);
    }
}
