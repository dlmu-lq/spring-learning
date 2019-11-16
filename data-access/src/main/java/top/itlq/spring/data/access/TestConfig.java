package top.itlq.spring.data.access;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.aspectj.AnnotationTransactionAspect;
import top.itlq.spring.data.access.service.BarService;
import top.itlq.spring.data.access.service.DefaultBarService;

import java.util.Properties;

@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
public class TestConfig {
    /**
     * 数据源
     * @return
     * @throws Exception
     */
    @Bean
    public BasicDataSource basicDataSource() throws Exception {
        Properties properties = new Properties();
        Properties jdbcProperties = new Properties();
        jdbcProperties.load(TestConfig.class.getClassLoader().getResourceAsStream("jdbc.properties"));
        properties.put("driverClassName", jdbcProperties.get("jdbc.driver"));
        properties.put("url", jdbcProperties.get("jdbc.url"));
        properties.put("username", jdbcProperties.get("jdbc.username"));
        properties.put("password", jdbcProperties.get("jdbc.password"));
        return BasicDataSourceFactory.createDataSource(properties);
    }

    /**
     * 事务管理器
     * @return
     * @throws Exception
     */
    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        PlatformTransactionManager txManager = new DataSourceTransactionManager(basicDataSource());
        AnnotationTransactionAspect.aspectOf().setTransactionManager(txManager);
        return txManager;
    }
    @Bean
    public DefaultBarService barService(){
        return new DefaultBarService();
    }
}
