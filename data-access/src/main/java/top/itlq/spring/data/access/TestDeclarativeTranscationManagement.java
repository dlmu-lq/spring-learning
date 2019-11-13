package top.itlq.spring.data.access;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.itlq.spring.data.access.dao.Foo;
import top.itlq.spring.data.access.service.FooService;

/**
 * xml配置或注解（@Transactional @EnableTransactionManagement）配置形式；
 * 使用AOP代理 TransactionInterceptor 与 PlatformTransactionManager 联系实现；
 * Caller -> AOP Proxy -> Transaction Advisor -> Custom Advisor -> Target Method
 *
 * 配置示例，见spring-tx.xml (包括各标签的意义可用属性默认值等)
 *      使用 tx:advice(tx:attributes tx:method) 设置方法名与对应事务属性
 *      使用 aop:config aop:pointcut aop:advisor将 txAdvice 与要应用所配置事务的方法对应上
 * 回滚操作：
 *      默认回滚抛出 RuntimeException(Error)的事务，不回滚需要 checked 的异常；
 *      但以上行为可以通过在 tx:method 的 rollback-for 和 no-rollback-for 属性配置；
 * 可以通过不同的切点匹配不同的txAdvice实现多种事务形式的控制；
 *
 *
 *
 */
public class TestDeclarativeTranscationManagement {

    private static final Log logger = LogFactory.getLog(TestDeclarativeTranscationManagement.class);

    @Test
    void test1(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-tx.xml");
        FooService fooService = context.getBean(FooService.class);
//        fooService.get("1");
        fooService.insert(new Foo());
    }

    @Test
    void test2(){}

}
