package top.itlq.spring.tests.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.itlq.spring.exampleBeans.scope.UserManager;

public class ScopeMain {
    @Test
    void testScopedProxy(){
        ApplicationContext context = new ClassPathXmlApplicationContext("scope/spring-scope.xml");
        UserManager userManager = context.getBean("userManager", UserManager.class);
        // 在xml中是否配置 aop scoped-proxy效果不同；
        // 可debug看看userManager注入的对象；
        userManager.printUser();
        userManager.printUser();
        userManager.printUser();
    }
}
