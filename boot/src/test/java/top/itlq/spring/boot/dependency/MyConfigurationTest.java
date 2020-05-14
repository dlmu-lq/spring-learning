package top.itlq.spring.boot.dependency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

//@SpringJUnitConfig({MyConfiguration.class})
@SpringBootTest
class MyConfigurationTest {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    void testCircularDependency(){
        BarService barService = applicationContext.getBean(BarService.class);
        FooService fooService = applicationContext.getBean(FooService.class);
        System.out.println(barService.getFooService());
        System.out.println(fooService.getBarService());
    }
}