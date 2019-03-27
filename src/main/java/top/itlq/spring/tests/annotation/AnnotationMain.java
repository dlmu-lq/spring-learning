package top.itlq.spring.tests.annotation;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.itlq.spring.exampleBeans.annotation.Talk;
import top.itlq.spring.exampleBeans.extension.RunningService;

public class AnnotationMain {
    @Test
    void a(){
        ApplicationContext context = new ClassPathXmlApplicationContext("annotation/test.xml");
        System.out.println(context.getBean("talk", Talk.class).getEvent());
    }
}
