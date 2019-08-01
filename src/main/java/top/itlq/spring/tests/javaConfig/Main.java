package top.itlq.spring.tests.javaConfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.itlq.spring.exampleBeans.javaConfig.*;

class Main {
    /**
     * java config full mode
     */
    @Test
    void test(){
        ApplicationContext context = new AnnotationConfigApplicationContext(
                Config.class
        );
        Assertions.assertEquals(
                context.getBean(IceCream.class).getChocolate(),
                context.getBean(Chocolate.class)
        );
    }

    /**
     * java config lite mode
     */
    @Test
    void testLite(){
        ApplicationContext context = new AnnotationConfigApplicationContext(
                LiteConfig.class
                , LiteDependency.class
        );
        Assertions.assertNotEquals(
                context.getBean(IceCream.class).getChocolate(),
                context.getBean(Chocolate.class)
        );
        Assertions.assertEquals(
                context.getBean(LiteConfig.class).getLiteDependency(),
                context.getBean(LiteDependency.class)
        );
    }
}
