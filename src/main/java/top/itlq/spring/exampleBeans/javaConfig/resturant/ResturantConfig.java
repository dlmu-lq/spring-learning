package top.itlq.spring.exampleBeans.javaConfig.resturant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResturantConfig {
    @Bean
    public Food f1(){
        return new Pork();
    }

    @Bean
    public Food f2(){
        return new Rice();
    }

    @Bean
    public Customer customer(Food f1){
        return new Customer(f1);
    }
}
