package top.itlq.spring.tests.classpathComponents;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("top.itlq.spring.exampleBeans.classpathComponents.components")
public class ComponentScanConfig {
}
