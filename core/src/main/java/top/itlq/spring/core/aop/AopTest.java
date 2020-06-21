package top.itlq.spring.core.aop;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.itlq.spring.core.aop2.TestService2;

public class AopTest {
    /**
     * xml 配置aspect 使用 aop
     */
    @Test
    void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("aop/aopTestContext.xml");
        IService testService = context.getBean(IService.class);
//        testService.test1();
//        testService.test2();
        testService.test3();

//        TestService2 testService2 = context.getBean(TestService2.class);
//        testService2.test1();
//        testService2.test2();
//        testService2.test3();
    }

    @Test
    void testJavaConfig(){
        ApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);

        TestService testService = context.getBean(TestService.class);
        testService.test1();
        testService.test2();
        testService.test3();

        TestService2 testService2 = context.getBean(TestService2.class);
        testService2.test1();
        testService2.test2();
        testService2.test3();
    }
}
