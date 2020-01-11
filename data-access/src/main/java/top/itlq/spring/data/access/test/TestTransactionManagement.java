package top.itlq.spring.data.access.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.itlq.spring.data.access.config.TransactionConfiguration;
import top.itlq.spring.data.access.dao.Foo;
import top.itlq.spring.data.access.service.DefaultBarService;
import top.itlq.spring.data.access.service.MyPosService;
import top.itlq.spring.data.access.service.NewPosService;

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
 *      开启：xml: tx:annotation-driven java: @EnableTransactionManagement 可设mode(proxy/aspect)
 *      1、@Transactional 可使用在类级别，接口定义上，类的public方法上，可以继承，但使用在子类上不会对父类产生影响；
 *      2、该注解用在非public方法上没有作用
 *      3、如果用在接口定义上，只能使用基于接口的代理，基于cglib类继承的和weaving-based 切面不好用（todo 验证这样也好用）；建议该注解用在具体类上；
 *      4、在非事务方法中调用同类的 @Transactional 方法不好用，因为不走代理；且必须完全初始化完成才能用；
 *      5、xml和java两种配置方式都只对同一个上下文ApplicationContext的注解类生效；
 * Transaction Propagation 多层事务方法的传播方式
 *      默认 REQUIRED ，内层事务方法使用外层事务方法的事务；有则使用，无则创建；异常影响外部事务；事务相关属性使用外部的，可检查不同抛错；
 *      REQUIRES_NEW，内层事务方法创建新的事务，外层被挂起；不影响外部事务，相对独立；
 *      NESTED，多个可回滚的保存点，内层事务可触发外层保存点，回滚一部分；仅支持 JDBC资源事务 保存点；
 * 通过Aspectj使用@Transactional注解未测试成功；
 * 使用TransactionTemplate编程式使用事务；构造方法里，注入 transactionManager;execute方法执行需要控制事务的代码；
 * 事务绑定事件，可用于监听不同阶段的事务方法内发布的事件；@TransactionalEventListener
 */
public class TestTransactionManagement {

    private AnnotationConfigApplicationContext context;

    @BeforeEach
    void createContext(){
        context = new AnnotationConfigApplicationContext(TransactionConfiguration.class);
    }

    /**
     * 测试java配置注解事务
     */
    @Test
    void test22(){
        DefaultBarService barService = context.getBean(DefaultBarService.class);
//        fooService.get("1");
        barService.insert(new Foo());
    }

    /**
     * 测试@Transactional继承及方法嵌套调用
     * 有事务方法里面嵌套调用没有问题；
     */
    @Test
    void testExtendsInvokeMethodTransaction(){
        MyPosService posService = context.getBean(MyPosService.class);
        posService.insert();
    }

    /**
     * 测试@Transactional继承及方法嵌套调用2
     * 无事务内部方法调用有事务方法也没用
     */
    @Test
    void testExtendsInvokeMethodTransaction2(){
        MyPosService posService = context.getBean(MyPosService.class);
        posService.insertWithTransaction();
    }

    /**
     * 测试不同方法创建新事务
     */
    @Test
    void test2TransactionRequiresNew(){
        NewPosService posService = context.getBean(NewPosService.class);
        posService.insertWith2Transaction();
    }
}
