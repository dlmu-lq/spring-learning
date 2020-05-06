package top.itlq.spring.core.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import top.itlq.spring.core.aop2.TestService2;

@EnableAspectJAutoProxy
@Configuration
public class AopConfig {
    @Bean
    public TestService testService(){
        return new TestService();
    }
    @Bean
    public TestService2 testService2(){
        return new TestService2();
    }
    @Bean
    public AnnotationAspect annotationAspect(){
        return new AnnotationAspect();
    }
}
