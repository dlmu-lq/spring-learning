package top.itlq.spring.tests.inheritance;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.itlq.spring.exampleBeans.inheritance.InheritanceTest;

public class Main {
    @Test
    void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("inheritance/inheritance.xml");
        System.out.println(context.getBean(InheritanceTest.class));
        try {
            System.out.println(context.getBean("template"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
