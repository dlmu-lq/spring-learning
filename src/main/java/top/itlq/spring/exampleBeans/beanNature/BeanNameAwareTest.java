package top.itlq.spring.exampleBeans.beanNature;

import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PostConstruct;

public class BeanNameAwareTest implements BeanNameAware {
    @PostConstruct
    public void init(){
        System.out.println("beanNameAware PostConstruct");
    }
    @Override
    public void setBeanName(String name) {
        System.out.println("beanNameAware " + name);
    }
}
