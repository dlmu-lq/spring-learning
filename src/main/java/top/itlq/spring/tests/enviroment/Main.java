package top.itlq.spring.tests.enviroment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import top.itlq.spring.exampleBeans.enviroment.DevTest;
import top.itlq.spring.exampleBeans.enviroment.EnvConfig;
import top.itlq.spring.exampleBeans.enviroment.ProTest;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EnvConfig.class)
@ActiveProfiles("dev")
public class Main {

    @Autowired(required = false)
    private ProTest proTest;

    @Autowired(required = false)
    private DevTest devTest;


    @Test
    void testSystemVariableProfile(){
        Assertions.assertNotNull(devTest);
        Assertions.assertNull(proTest);
    }
}
