package top.itlq.spring.core.ioc.tests.extension;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.itlq.spring.core.ioc.beans.extension.Apple;
import top.itlq.spring.core.ioc.beans.extension.FactoryBeanTest;
import top.itlq.spring.core.ioc.beans.extension.RunningService;

public class ExtensionMain {
    @Test
    void a(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc/extension/test-bean-post-processor.xml");
        RunningService runningService = context.getBean("runningService",RunningService.class);
    }

    @Test
    void b(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc/extension/test-bean-factory-post-processor.xml");
        RunningService runningService = context.getBean("runningServiceB",RunningService.class);
        System.out.println(runningService);
    }

    @Test
    void testInterfaceFactoryBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc/extension/factory-bean.xml");
        System.out.println(context.getBean(Apple.class));
        System.out.println(context.getBean(FactoryBeanTest.class));
        System.out.println(context.getBean("factoryBeanTest"));
        System.out.println(context.getBean("&factoryBeanTest"));
    }
}
