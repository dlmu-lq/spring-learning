package top.itlq.spring.core.ioc.tests.inheritance;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.itlq.spring.core.ioc.beans.inheritance.InheritanceTest;

public class Main {
    @Test
    void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ioc/inheritance/inheritance.xml");
        System.out.println(context.getBean(InheritanceTest.class));
        try {
            System.out.println(context.getBean("template"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
