package top.itlq.spring.configure.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

/**
 * 使用spring-boot-autoconfigure
 * @see org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
 * @see org.springframework.boot.autoconfigure.jdbc.DataSourceConfiguration
 * @see org.springframework.boot.autoconfigure.jdbc.DataSourceConfiguration.Hikari
 * @see org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
 */
@Configuration
@ConditionalOnClass(DataSource.class)
public class DataSourceAutoConfiguration {
//    @ConditionalOnClass(HikariDataSource.class)
//    static class
}
