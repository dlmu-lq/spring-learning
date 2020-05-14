package top.itlq.spring.core.ioc.dependency;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCircualDependency {
    @Test
    void testXmlSetterDependecy(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ioc/dependency/spring.xml");
        BarService barService = classPathXmlApplicationContext.getBean(BarService.class);
        FooService fooService = classPathXmlApplicationContext.getBean(FooService.class);
        System.out.println(barService.getFooService());
        System.out.println(fooService.getBarService());
    }

    @Test
    void testJavaSetterDependency(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfiguration.class);
        BarService barService = applicationContext.getBean(BarService.class);
        FooService fooService = applicationContext.getBean(FooService.class);
        BazService bazService = applicationContext.getBean(BazService.class);
        System.out.println(barService.getFooService());
        System.out.println(fooService.getBarService());
        System.out.println(bazService.getFooService());
    }
}
