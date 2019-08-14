package top.itlq.spring.ioc.beans.beanNature;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class TestCallBack implements InitializingBean, DisposableBean {
    /**
     * 初始化方法
     */

    @PostConstruct
    public void init1(){
        System.out.println("init1 from @PostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("init2 from InitializingBean.afterPropertiesSet()");
    }

    public void init3(){
        System.out.println("init3 from xml bean init-method");
    }

    /**
     * 销毁方法
     * @throws Exception
     */
    @PreDestroy
    public void destroy1(){
        System.out.println("destroy1 from @PreDestroy");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("destroy2 from DisposableBean.destroy()");
    }

    public void destroy3(){
        System.out.println("destroy3 from xml destroy-method");
    }
}
