package top.itlq.spring.core.ioc.tests.classpathComponents;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class ClasspathComponentsMain {
    /**
     * @Import
     */
    @Test
    void a(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ImportConfig.class);
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        //@Import使用全限定类名作为默认bean name
        System.out.println(context.getBean("top.itlq.spring.core.ioc.beans.classpathComponents.base.Drink"));
    }

    /**
     * @Configuration @Bean工厂
     */
    @Test
    void b(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationBeanConfig.class);
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        // 方法签名作为bean name
        System.out.println(context.getBean("anyDrink"));
    }

    /**
     * 自动扫描 class形式
     * @Configuration @ComponentScan
     */
    @Test
    void c(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        // 非限定类名首字母变小写作为默认bean name
        System.out.println(context.getBean("barService"));
        System.out.println(context.getBean("drinkMapper"));
    }

    /**
     * 自动扫描 xml形式
     * xml component scan
     */
    @Test
    void d(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ioc/classpathComponents/test.xml");
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        // 非限定类名首字母变小写作为默认bean name
        System.out.println(context.getBean("barService"));
        System.out.println(context.getBean("drinkMapper"));
    }

    /**
     * 自动扫描过滤器
     * xml形式
     */
    @Test
    void e(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ioc/classpathComponents/test-filters.xml");
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
    }
}
