package top.itlq.spring.test.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import top.itlq.spring.test.TestConfig;

//@SpringJUnitConfig(locations = "classpath:spring-test.xml")
@SpringJUnitConfig //  不指定class和location，默认使用 classpath:/top/itlq/spring/test/dao/UserDaoXmlTest-context.xml
class UserDaoXmlTest {

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