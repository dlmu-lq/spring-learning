package top.itlq.spring.ioc.tests.classpathComponents;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("top.itlq.spring.ioc.beans.classpathComponents.components")
public class ComponentScanConfig {
}
