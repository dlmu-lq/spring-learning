package top.itlq.spring.core.ioc.tests.applicationContextAdditional;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.itlq.spring.core.ioc.beans.applicationContextAdditional.*;

import java.io.IOException;
import java.util.Locale;

public class Main {
    @Test
    void testMessageSource(){
        MessageSource messageSource = new ClassPathXmlApplicationContext("ioc/applicationContextAdditional/message-source-test.xml");
        System.out.println(messageSource.getMessage("testMessage",null, null));
    }

    @Test
    void testMessageSource2(){
        MessageSource messageSource = new ClassPathXmlApplicationContext("ioc/applicationContextAdditional/message-source-test.xml");
        System.out.println(messageSource.getMessage("arguments.required",null, Locale.UK));
        System.out.println(messageSource.getMessage("arguments.required",new Object[]{"a"}, Locale.UK));
        System.out.println(messageSource.getMessage("arguments.required",new Object[]{"a"}, null));
    }

    @Test
    void testMessageSourceAware(){
        new AnnotationConfigApplicationContext(MessageSourceTest.class);
    }

    @Test
    void testReloadMessageSource() throws Exception{
        MessageSource messageSource = new ClassPathXmlApplicationContext("ioc/applicationContextAdditional/beans.xml");
        // 可在xml中设置额defaultEncding为utf-8，不然需要转换编码
//        System.out.println(new String(messageSource.getMessage("lack.params",null, null)
//                .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        System.out.println(messageSource.getMessage("lack.params",null, null));
    }

    @Test
    void testPublishEvent(){
        ApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        EmailService emailService = context.getBean(EmailService.class);
        emailService.sendEmail("123@gmail.com", "123");
        emailService.sendEmail("456@gmail.com", "456");
        emailService.sendEmail("123@163.com", "163");
    }

    @Test
    void testPublishObjectEvent(){
        ApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        context.getBean(EmailService.class).sendObjectEvent();
    }

    @Test
    void testGenericEvents(){
        ApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        context.getBean(PersonService.class).createPerson();
    }

    @Test
    void testResourceLoader() throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ResourceLoaderTest.class);
        context.getBean(ResourceLoaderTest.class).loadResource("/ioc/applicationContextAdditional/exceptions.properties");
    }
}
