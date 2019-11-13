package top.itlq.spring.core.ioc.tests.beanNature;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.itlq.spring.core.ioc.beans.beanNature.TestCallBack;

import java.util.concurrent.TimeUnit;

/**
 * beand的nature测试 生命周期等回调；
 */
public class BeanNatureMain {
    @Test
    void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "ioc/beanNature/spring-bean-nature.xml");
        TestCallBack testCallBack = context.getBean(TestCallBack.class);
        context.close();
    }

    @Test
    void testLifecycle() throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "ioc/beanNature/spring-bean-nature.xml");
        context.registerShutdownHook();
        System.out.println("context starting");
        context.start();
        TimeUnit.MILLISECONDS.sleep(1500);
        System.out.println("context refreshing");
        context.refresh();
        TimeUnit.MILLISECONDS.sleep(1500);
        System.out.println("context closing");
    }

    @Test
    void testAware() throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "ioc/beanNature/spring-bean-nature.xml");
    }
}
