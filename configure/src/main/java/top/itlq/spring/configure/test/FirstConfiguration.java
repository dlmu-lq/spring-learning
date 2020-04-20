package top.itlq.spring.configure.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirstConfiguration {
    @Bean
    public BaseUserMapper userMapper(){
        return new BaseUserMapper() {};
    }
}
