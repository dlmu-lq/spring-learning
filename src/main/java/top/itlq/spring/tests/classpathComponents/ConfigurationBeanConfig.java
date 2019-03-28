package top.itlq.spring.tests.classpathComponents;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import top.itlq.spring.exampleBeans.classpathComponents.base.Drink;
import top.itlq.spring.exampleBeans.classpathComponents.base.Water;

@Configuration
public class ConfigurationBeanConfig {
    @Bean
    @DependsOn({"water","water2"})
    public Drink anyDrink(){
        System.out.println("anyDrink");
        return new Drink();
    }

    @Bean
    public Water water(){
        System.out.println("water");
        return new Water();
    }

    @Bean
    public Water water2(){
        System.out.println("water2");
        return new Water();
    }
}
