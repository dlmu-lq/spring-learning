package top.itlq.spring.data.access;

/**
 * xml配置或注解（@Transactional @EnableTransactionManagement）配置形式；
 * 使用AOP代理 TransactionInterceptor 与 PlatformTransactionManager 联系实现；
 * Caller -> AOP Proxy -> Transaction Advisor -> Custom Advisor -> Target Method
 */
public class TestDeclarativeTranscationManagement {
}
