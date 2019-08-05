package top.itlq.spring.exampleBeans.javaConfig.profile;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan(basePackages = "top.itlq.spring.exampleBeans.javaConfig.profile")
public class ProfileConfig {
}
