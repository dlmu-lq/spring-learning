package top.itlq.spring.tests.classpathComponents;

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
        System.out.println(context.getBean("top.itlq.spring.exampleBeans.classpathComponents.base.Drink"));
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
     * xml component scan
     */
    @Test
    void d(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpathComponents/test.xml");
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        // 非限定类名首字母变小写作为默认bean name
        System.out.println(context.getBean("barService"));
        System.out.println(context.getBean("drinkMapper"));
    }
}
