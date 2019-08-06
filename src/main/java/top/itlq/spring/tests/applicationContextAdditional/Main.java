package top.itlq.spring.tests.applicationContextAdditional;

import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import top.itlq.spring.exampleBeans.applicationContextAdditional.MessageSourceTest;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class Main {
    @Test
    void testMessageSource(){
        MessageSource messageSource = new ClassPathXmlApplicationContext("message-source-test.xml");
        System.out.println(messageSource.getMessage("testMessage",null, null));
    }

    @Test
    void testMessageSource2(){
        MessageSource messageSource = new ClassPathXmlApplicationContext("message-source-test.xml");
        System.out.println(messageSource.getMessage("arguments.required",null, Locale.UK));
        System.out.println(messageSource.getMessage("arguments.required",new Object[]{"a"}, Locale.UK));
        System.out.println(messageSource.getMessage("arguments.required",new Object[]{"a"}, null));
    }

    @Test
    void testMessageSourceAware(){
        new AnnotationConfigApplicationContext(MessageSourceTest.class);
    }

    @Test
    void testReloadMessageSourceAware() throws Exception{
        MessageSource messageSource = new ClassPathXmlApplicationContext("message-source-test.xml");
        System.out.println(new String(messageSource.getMessage("lack.params",null, null)
                .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
    }
}
