package top.itlq.spring.data.access.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.aspectj.AnnotationTransactionAspect;

import javax.sql.DataSource;

/**
 * 开启事务支持
 */
@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
@Import({DataSourceConfiguration.class, ServiceConfiguration.class, MybatisConfiguration.class})
public class TransactionConfiguration {
    /**
     * 事务管理器
     * @return
     * @throws Exception
     */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) throws Exception {
        PlatformTransactionManager txManager = new DataSourceTransactionManager(dataSource);
        AnnotationTransactionAspect.aspectOf().setTransactionManager(txManager);
        return txManager;
    }
}
