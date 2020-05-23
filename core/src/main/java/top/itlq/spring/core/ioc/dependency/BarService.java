package top.itlq.spring.core.ioc.dependency;

import org.springframework.beans.factory.annotation.Autowired;

public class BarService {

    private FooService fooService;

    public BarService(){

    }

    @Autowired
    public void setFooService(FooService fooService){
        this.fooService = fooService;
    }

    public FooService getFooService() {
        return fooService;
    }
}
