package top.itlq.spring.tests.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.itlq.spring.exampleBeans.scope.UserManager;

public class ScopeMain {
    /**
     * 在xml中是否配置 aop scoped-proxy效果不同；
     * 可debug看看userManager注入的对象；
     * 使用jdk-based代理时必须通过接口注入；
     */
    @Test
    void testScopedProxy(){
        ApplicationContext context = new ClassPathXmlApplicationContext("scope/spring-scope.xml");
        UserManager userManager = context.getBean("userManager", UserManager.class);
        userManager.printUser();
        userManager.printUser();
        userManager.printUser();
    }
}
