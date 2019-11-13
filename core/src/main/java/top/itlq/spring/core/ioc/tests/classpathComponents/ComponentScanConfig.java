package top.itlq.spring.core.ioc.tests.classpathComponents;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("top.itlq.spring.core.ioc.beans.classpathComponents.components")
public class ComponentScanConfig {
}
