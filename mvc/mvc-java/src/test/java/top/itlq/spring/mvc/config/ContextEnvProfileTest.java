package top.itlq.spring.mvc.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import top.itlq.spring.mvc.config.RootConfig;

import java.util.Arrays;

@SpringJUnitWebConfig(RootConfig.class)
public class ContextEnvProfileTest {

    @Autowired
    private ApplicationContext context;

    /**
     * 通过更改maven profile运行看不同结果
     */
    @Test
    void testMavenActiveProfile(){
        System.out.println(Arrays.toString(context.getEnvironment().getActiveProfiles()));
    }
}
