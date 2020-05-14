package top.itlq.spring.boot.dependency;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
public class MyConfiguration {

    @Bean
    public FooService fooService(){
        return new FooService();
    }

    @Bean
    public BarService barService(){
        return new BarService();
    }
}
