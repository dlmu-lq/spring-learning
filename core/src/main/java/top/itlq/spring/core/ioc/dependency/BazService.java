package top.itlq.spring.core.ioc.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class BazService {

    private FooService fooService;

    public BazService(FooService fooService){
        this.fooService = fooService;
    }

    public FooService getFooService() {
        return fooService;
    }
}
