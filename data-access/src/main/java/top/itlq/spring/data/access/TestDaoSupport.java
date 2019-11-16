package top.itlq.spring.data.access;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import top.itlq.spring.data.access.service.TestDaoExceptionService;

import java.util.Properties;

/**
 * spring提供的时访问数据更简单的支持
 *      异常整合； 封装不同持久层框架的异常，可以使异常独立于底层框架；
 *      @Annotation 注解
 */
public class TestDaoSupport {

    @Configuration
    @ComponentScan(basePackages = "top.itlq.spring.data.access.service")
    public static class Config{
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
    }

    @Test
    public void test11() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        System.out.println(context.getBean(TestDaoExceptionService.class).getUsers());
        System.out.println(context.getBean(TestDaoExceptionService.class).getUsersWithExceptions());
    }
}
