package top.itlq.spring.ioc.tests.annotation;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.itlq.spring.ioc.beans.annotation.Concert;
import top.itlq.spring.ioc.beans.annotation.Talk;

public class AnnotationMain {
    @Test
    void a(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc/annotation/test.xml");
        System.out.println(context.getBean("talk", Talk.class).getEvent());
        System.out.println(context.getBean("concert", Concert.class).music1);
    }
}
