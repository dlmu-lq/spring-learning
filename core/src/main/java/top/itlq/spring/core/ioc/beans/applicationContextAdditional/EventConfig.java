package top.itlq.spring.core.ioc.beans.applicationContextAdditional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class EventConfig {
    /**
     * 邮件服务类，用来测试发送事件
     * @return
     */
    @Bean
    public EmailService emailService(){
        List<String> blackList = Arrays.asList(
                "123@qq.com",
                "123@gmail.com",
                "123@163.com"
        );
        return new EmailService(blackList);
    }

    /**
     * 用来接收事件
     * @return
     */
    @Bean
    public BlackListNotifier blackListNotifier(){
        return new BlackListNotifier();
    }

    @Bean
    public BlackListNotifier2 blackListNotifier2(){
        return new BlackListNotifier2();
    }

    /**
     * 接收实体类创建事件，有泛型版本
     * @return
     */
    @Bean
    public EntityCreatedNotifier entityCreatedNotifier(){
        return new EntityCreatedNotifier();
    }

    /**
     * 发送实体类创建事件
     */
    @Bean
    public PersonService personService(ApplicationEventPublisher applicationEventPublisher){
        return new PersonService(applicationEventPublisher);
    }
}
