package top.itlq.spring.tests.applicationContextAdditional;

import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    @Test
    void testMessageSource(){
        MessageSource messageSource = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println(messageSource.getMessage("testMessage",null, null));
    }
}
