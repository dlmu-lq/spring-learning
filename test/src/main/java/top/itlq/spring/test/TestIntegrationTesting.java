package top.itlq.spring.test;

/**
 * spring集成测试
 *      目标与支持
 *          上下文管理和缓存 context
 *          依赖注入 Test 的固定部分 如 事务代理，DataSource，
 *          事务管理 TestContext框架 对一个test开启事务，完成后回滚；
 *          集成测试的支持类 ApplicationContext JdbcTemplate等
 *      JDBC 测试支持
 *          JdbcTestUtils 的工具方法 （AbstractTransactionalJUnit4SpringContextTests） (可结合embedded database)
 *      注解
 *          spring测试注解
 *              @BootstrapWith @ContextConfiguration @WebAppConfiguration(value resourcePath) @ContextHierarchy
 *              @ActiveProfiles @TestPropertySource @DirtiesContext @TestExecutionListeners
 *              @Commit @Rollback @BeforeTransaction @AfterTransaction
 *              @Sql @SqlConfig @SqlMergeMode @SqlGroup
 *          标准注解支持
 *
 *          spring junit4 测试注解
 *              @
 *          spring junit jupiter 测试注解
 *              @SpringJunitConfig @ContextConfiguration和@ExtendWith(SpringExtension.class)
 *              @SpringJunitWebConfig
 *              @EnabledIf @DisabledIf (SpEL表达式)
 *          测试用配置注解；
 *      Spring TestContext Framework (与测试框架无关，可用jupiter，junit4，testpng等)
 *          如果context同时有xml和Component class，可在configuration中@ImportResource导入xml
 *          可用 @ContextConfiguration 的initializers属性；
 *          环境与属性 @ActiveProfiles @TestPropertySource
 *          context缓存，test suite， maven test 的 forkMode
 *          Request and Session-scoped 的bean测试 （设置context的MockHttpServletRequest 和 MockHttpSession）
 *          jupiter支持，构造器，方法等依赖注入；@EnabledIf @DisabledIf SpEl; 复合注解；
 *      Spring MVC Test Framework (非运行容器的测试)
 *          MockMvc builders 模式创建，执行，测试；可只测controller；todo MockMvc创建时可配置session
 *          每次请求测试perform的相同属性一般可以放在创建配置MockMvc处；
 *
 *
 */
public class TestIntegrationTesting {
}
