package top.itlq.spring.data.access.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 配置简单数据源
 */
@Configuration
public class DataSourceConfiguration {
    /**
     * 数据源
     * @return
     * @throws Exception
     */
    @Bean
    public BasicDataSource basicDataSource() throws Exception {
        Properties properties = new Properties();
        Properties jdbcProperties = new Properties();
        jdbcProperties.load(getClass().getResourceAsStream("/jdbc.properties"));
        properties.put("driverClassName", jdbcProperties.get("jdbc.driver"));
        properties.put("url", jdbcProperties.get("jdbc.url"));
        properties.put("username", jdbcProperties.get("jdbc.username"));
        properties.put("password", jdbcProperties.get("jdbc.password"));
        return BasicDataSourceFactory.createDataSource(properties);
    }
}
