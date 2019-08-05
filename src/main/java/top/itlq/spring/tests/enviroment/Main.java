package top.itlq.spring.tests.enviroment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.convert.Property;
import top.itlq.spring.exampleBeans.enviroment.PropertySourceMain;
import top.itlq.spring.exampleBeans.enviroment.TestBean;

import java.util.Map;
import java.util.Properties;

public class Main {

    /**
     * 包含系统属性（包括命令行参数），和操作系统环境变量
     */
    @Test
    void test(){
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext();
        Properties properties = System.getProperties();
        System.out.println(properties);
        for(Map.Entry<Object,Object> entry:properties.entrySet()){
            Assertions.assertEquals(context.getEnvironment().getProperty(entry.getKey().toString()), entry.getValue());
        }
        Map<String,String> map = System.getenv();
        System.out.println(map);
        for(Map.Entry<String,String> entry:map.entrySet()){
            Assertions.assertEquals(context.getEnvironment().getProperty(entry.getKey()), entry.getValue());
        }
    }

    @Test
    void testPropertySource(){
        ApplicationContext context = new AnnotationConfigApplicationContext(
                PropertySourceMain.class
        );
        System.out.println(context.getBean(TestBean.class));
    }
}
