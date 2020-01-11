package top.itlq.spring.data.access.test;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import top.itlq.spring.data.access.dao.Foo;
import top.itlq.spring.data.access.service.BarService;
import top.itlq.spring.data.access.service.FooService;

public class TestXmlTransactionManagement {
    /**
     * 测试xml数据源是否创建成功
     */
    @Test
    void testDataSource(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-tx.xml");
        BasicDataSource basicDataSource = context.getBean("basicDataSource", BasicDataSource.class);
        System.out.println(basicDataSource.getUrl());
    }

    /**
     * 测试 xml tx:advice 通过切面完成事务
     */
    @Test
    void testAdviceTransaction(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-tx.xml");
        FooService fooService = context.getBean(FooService.class);
//        fooService.get("1");
        fooService.insert(new Foo());
    }

    /**
     * xml 测试 @Transactional
     */
    @Test
    void testAnnotationTransaction(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-tx.xml");
        BarService barService = context.getBean(BarService.class);
        barService.get("1");
//        barService.insert(new Foo());
    }

    /**
     * 测试编程时事务
     * @see TransactionTemplate
     */
    @Test
    void testProgramTransaction(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-tx.xml");

        PlatformTransactionManager transactionManager = context.getBean(PlatformTransactionManager.class);
        // 一般放在需要控制层的构造方法里，注入 transactionManager
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        // 可设置事务方法相关的属性
//        transactionTemplate.setPropagationBehavior();
//        transactionTemplate.setIsolationLevel();
        transactionTemplate.execute((TransactionCallback<Object>) status -> {
//            return barService.get("1");
            status.setRollbackOnly();
            return null;
        });
//        barService.insert(new Foo());
    }

}
