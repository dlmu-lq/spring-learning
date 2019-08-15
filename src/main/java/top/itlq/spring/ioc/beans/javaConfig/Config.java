package top.itlq.spring.ioc.beans.javaConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liqiang
 */
@Configuration
public class Config {
    @Bean
    public Chocolate chocolate(){
        return new Chocolate();
    }

    @Bean(initMethod = "init")
    public IceCream iceCream(){
        // 直接使用chocolate bean注入依赖；lite模式@Bean不可以；
        return new IceCream(chocolate());
    }

    @Bean
    public IceCream iceCream2(Chocolate chocolate2){
        // 直接使用chocolate bean注入依赖；lite模式@Bean不可以；
        return new IceCream(chocolate2);
    }
}
