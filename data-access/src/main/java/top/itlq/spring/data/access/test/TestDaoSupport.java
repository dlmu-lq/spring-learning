package top.itlq.spring.data.access.test;


import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.itlq.spring.data.access.config.TransactionConfiguration;
import top.itlq.spring.data.access.service.TestDaoExceptionService;

/**
 * spring提供的时访问数据更简单的支持
 *      异常整合； 封装不同持久层框架的异常，可以使异常独立于底层框架；
 *      @Annotation 注解
 */
public class TestDaoSupport {

    @Test
    public void test11() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TransactionConfiguration.class);
        System.out.println(context.getBean(TestDaoExceptionService.class).getUsers());
        System.out.println(context.getBean(TestDaoExceptionService.class).getUsersWithExceptions());
    }

    @Test
    public void test12() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-tx.xml");
        context.getBean(TestDaoExceptionService.class).testException();
    }
}
