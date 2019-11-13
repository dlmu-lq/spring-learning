package top.itlq.spring.data.access;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestTransactionManager {
    @Test
    void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-tx.xml");
        BasicDataSource basicDataSource = context.getBean("basicDataSource", BasicDataSource.class);
        System.out.println(basicDataSource.getUrl());
    }
}
