package top.itlq.spring.core.ioc.tests.enviroment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import top.itlq.spring.core.ioc.beans.enviroment.DevTest;
import top.itlq.spring.core.ioc.beans.enviroment.EnvConfig;
import top.itlq.spring.core.ioc.beans.enviroment.ProTest;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EnvConfig.class)
@ActiveProfiles("dev")// 使用系统环境变量配置时，idea需重启
public class SpringTestMain {

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
