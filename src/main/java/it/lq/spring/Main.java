package it.lq.spring;

import it.lq.spring.beans.HelloWorld;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String...args){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-ioc-test.xml");
        HelloWorld helloWorld = applicationContext.getBean("helloWorld", HelloWorld.class);
        helloWorld.hello();
    }
}
