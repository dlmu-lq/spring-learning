package top.itlq.spring.test.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import top.itlq.spring.test.TestConfig;

@SpringJUnitConfig(TestConfig.class)
class UserDaoTestJupiter {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void test1(){
        System.out.println(userDao);
        System.out.println(applicationContext);
    }
}