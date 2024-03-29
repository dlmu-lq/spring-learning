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

        TestService2 testService2 = context.getBean(TestService2.class);
        testService2.test1();
        testService2.test2();
        testService2.test3();
    }

    @Test
    void testJavaConfig(){
        ApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);


        // TestService 代理 实现的是 IService接口，直接获取TestService是获取不到的，类型不正确
//        TestService testService = context.getBean(TestService.class);
        IService testService = (IService) context.getBean("testService");
        testService.test1();
        // jdk代理接口中没有test2方法，就没法使用spring容器管理了
//        testService.test2();
        testService.test3();

        TestService2 testService2 = context.getBean(TestService2.class);
        testService2.test1();
        testService2.test2();
        testService2.test3();
    }
}
