package top.itlq.spring.boot.test;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(FirstConfiguration.class)
public class SecondConfiguration {
    @Bean
    @ConditionalOnBean(UserMapper.class)
    public KeyMapper keyMapper(){
        return new KeyMapper(){};
    }
}
