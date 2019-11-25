package top.itlq.spring.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"top.itlq.spring.test.dao", "top.itlq.spring.test.service"})
public class TestConfig {

}
