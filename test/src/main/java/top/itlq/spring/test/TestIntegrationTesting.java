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
 *
 *
 */
public class TestIntegrationTesting {
}
