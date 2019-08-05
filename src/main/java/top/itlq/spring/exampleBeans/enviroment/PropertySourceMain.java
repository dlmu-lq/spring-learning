package top.itlq.spring.exampleBeans.enviroment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@PropertySource("classpath:/enviroment/app.properties")
@Configuration
public class PropertySourceMain {

    /**
     * 直接通过占位符注入，或通过环境获取
     * @param environment
     * @param age
     * @return
     */
    @Bean
    public TestBean testBean(Environment environment, @Value("${testBean.age}") int age){
        TestBean testBean = new TestBean();
        testBean.setName(environment.getProperty("testBean.name"));
        testBean.setAge(age);
        return testBean;
    }

}
