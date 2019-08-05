package top.itlq.spring.exampleBeans.javaConfig.resturant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ResturantConfig.class)
public class ResturantConfig2 {
    @Bean
    public Customer f1(){
        return new Customer(new Pork());
    }

    @Bean
    public Customer f2(Food f1){
        return new Customer(f1);
    }
}
