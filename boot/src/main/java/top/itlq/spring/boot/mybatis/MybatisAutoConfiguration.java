package top.itlq.spring.boot.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author liqiang
 * {@link org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration}
 * {@link SqlSessionFactory}
 * {@link SqlSessionTemplate}
 */
@Configuration
@ConditionalOnClass({DataSource.class, SqlSessionFactory.class, SqlSessionTemplate.class})
public class MybatisAutoConfiguration {

    @Configuration
    static class MapperScannerConfigurerConfiguration{
        @Bean
        MapperScannerConfigurer mapperScannerConfigurer(){
            MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
            // 多个数据源时，需要设置SqlSessionTemplateName
            mapperScannerConfigurer.setBasePackage("top.itlq.**.orm");
            return mapperScannerConfigurer;
        }
    }
}
