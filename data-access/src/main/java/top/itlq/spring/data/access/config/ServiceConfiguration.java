package top.itlq.spring.data.access.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 开启扫描service包
 */
@Configuration
@ComponentScan(basePackages = "top.itlq.spring.data.access.service")
public class ServiceConfiguration {
}
