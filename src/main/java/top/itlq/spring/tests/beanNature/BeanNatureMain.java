package top.itlq.spring.tests.beanNature;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.itlq.spring.exampleBeans.beanNature.TestCallBack;

/**
 * beand的nature测试 生命周期等回调；
 */
public class BeanNatureMain {
    @Test
    void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "beanNature/spring-bean-nature.xml");
        TestCallBack testCallBack = context.getBean(TestCallBack.class);
        context.close();
    }
}
