package top.itlq.spring.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import top.itlq.spring.test.TestConfig;
import top.itlq.spring.test.dao.UserDao;

import java.util.Arrays;

@SpringJUnitWebConfig(TestConfig.class)
class WebAppTestJupiter {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void test1(){
        System.out.println(userDao);
        System.out.println(applicationContext);
        System.out.println(Arrays.toString(applicationContext.getEnvironment().getActiveProfiles()));
    }
}