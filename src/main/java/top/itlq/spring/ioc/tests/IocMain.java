package top.itlq.spring.ioc.tests;

import top.itlq.spring.ioc.beans.initialization.HelloWorld;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring ioc basic tests
 */
class IocMain {

    /**
     * 初始化容器，bean的基本创建过程，获取bean（别名）
     */
    @Test
    void helloWorld(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc/initialization/spring-hello-world.xml");
        HelloWorld helloWorld = applicationContext.getBean("helloWorld", HelloWorld.class);
        helloWorld.hello();
        // 别名
        HelloWorld helloWorld3 = applicationContext.getBean("helloWorld3",HelloWorld.class);
        helloWorld3.hello();
    }

    /**
     * spring-initialize-arguments.xml,容器创建
     */
    @Test
    void initializeWithParams(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc/initialization/spring-initialize-arguments.xml");
    }

    /**
     * 导入各个xml，初始化容器
     */
    @Test
    void all(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc/applicationContextAdditional/spring-main.xml");
    }

}
