package top.itlq.spring.data.access.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import top.itlq.spring.data.access.orm.PosMapper;

import javax.sql.DataSource;

/**
 * 配置mybatis
 */
@Configuration
@Import({DataSourceConfiguration.class, ServiceConfiguration.class})
public class MybatisConfiguration {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        return factory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        // 多个数据源时，需要设置SqlSessionTemplateName
        mapperScannerConfigurer.setBasePackage("top.itlq.**.orm");
        return mapperScannerConfigurer;
    }

    /**
     * 测试间隔查询
     */
    @Test
    public void testInterval(){
        ApplicationContext context = new AnnotationConfigApplicationContext(MybatisConfiguration.class);
        PosMapper posMapper = context.getBean(PosMapper.class);
        System.out.println(posMapper.testInterval().size());
    }
}
