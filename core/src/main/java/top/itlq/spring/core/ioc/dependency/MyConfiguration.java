package top.itlq.spring.core.ioc.dependency;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

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

    @Bean
    public BazService bazService(@Lazy FooService fooService){
        return new BazService(fooService);
    }
}
