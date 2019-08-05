package top.itlq.spring.tests.javaConfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.itlq.spring.exampleBeans.javaConfig.*;
import top.itlq.spring.exampleBeans.javaConfig.profile.ProfileConfig;
import top.itlq.spring.exampleBeans.javaConfig.profile.ProfileDevTest;
import top.itlq.spring.exampleBeans.javaConfig.profile.ProfileProTest;
import top.itlq.spring.exampleBeans.javaConfig.profile.ProfileTest;
import top.itlq.spring.exampleBeans.javaConfig.resturant.ResturantConfig;
import top.itlq.spring.exampleBeans.javaConfig.resturant.ResturantConfig2;

import java.util.Arrays;

class Main {
    /**
     * java config full mode
     */
    @Test
    void test(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                Config.class
        );
        context.registerShutdownHook();
        Assertions.assertEquals(
                context.getBean(IceCream.class).getChocolate(),
                context.getBean(Chocolate.class)
        );
        // 默认 @Bean 方法会将 bean的close方法自动做为销毁回调，可用 destroyMethod=""关闭
        context.close();
    }

    /**
     * java config lite mode
     */
    @Test
    void testLite(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
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
        context.close();
    }

    // 多个配置类中同名bean会覆盖
    @Test
    void testResturant(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                ResturantConfig.class
                , ResturantConfig2.class
        );
        System.out.println(context.getBean("f1"));
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        context.close();
    }

    /**
     * profile
     */
    @Test
    void testProfile(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("dev");
        context.register(ProfileConfig.class);
        context.refresh();
        Assertions.assertTrue(context.getBean(ProfileTest.class) instanceof ProfileDevTest);

        context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("pro");
        context.register(ProfileConfig.class);
        context.refresh();
        Assertions.assertTrue(context.getBean(ProfileTest.class) instanceof ProfileProTest);
    }
}
