package top.itlq.spring.core.ioc.beans.extension;

import org.springframework.beans.factory.FactoryBean;

public class FactoryBeanTest implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new Apple();
    }

    @Override
    public Class<?> getObjectType() {
        return Apple.class;
    }
}
