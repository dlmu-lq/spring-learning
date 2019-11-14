package top.itlq.spring.data.access;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.itlq.spring.data.access.dao.Foo;
import top.itlq.spring.data.access.service.BarService;
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
 * 注解配置：
 *      开启：xml: tx:annotation-driven java: @EnableTransactionManagement 可设mode
 *      1、@Transactional 可使用在类级别，接口定义上，类的public方法上，使用在子类上不会对父类产生影响；
 *      2、该注解用在非public方法上没有作用
 *      3、如果用在接口定义上，只能使用基于接口的代理，基于cglib类继承的和weaving-based 切面不好用（todo 验证这样也好用）；建议该注解用在具体类上；
 *      4、在方法中调用同类的 @Transactional 方法不好用，因为不走代理；且必须完全初始化完成才能用；
 *      5、xml和java两种配置方式都只对同一个上下文ApplicationContext的注解类生效；
 * Transaction Propagation 多层事务方法的传播方式
 *      默认 REQUIRED ，内层事务方法使用外层事务方法的事务；有则使用，无则创建；异常影响外部事务；事务相关属性使用外部的，可检查不同抛错；
 *      REQUIRES_NEW，内层事务方法创建新的事务，外层被挂起；不影响外部事务；
 *      NESTED，多个可回滚的保存点，内层事务可触发外层保存点，回滚一部分；仅支持 JDBC 保存点；
 */
public class TestDeclarativeTranscationManagement {

    private static final Log logger = LogFactory.getLog(TestDeclarativeTranscationManagement.class);

    /**
     * 测试 tx:advice 通过切面完成事务
     */
    @Test
    void test1(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-tx.xml");
        FooService fooService = context.getBean(FooService.class);
//        fooService.get("1");
        fooService.insert(new Foo());
    }

    /**
     * 测试 @Transactional
     */
    @Test
    void test2(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-tx.xml");
        BarService barService = context.getBean(BarService.class);
        barService.get("1");
//        barService.insert(new Foo());
    }

    @Test
    void test21(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        BarService barService = context.getBean(BarService.class);
//        fooService.get("1");
        barService.insert(new Foo());
    }

}
