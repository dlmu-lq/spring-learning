package top.itlq.spring.core.ioc.dependency;

import org.springframework.beans.factory.annotation.Autowired;

public class FooService {

    @Autowired
    private BarService barService;

    public FooService(){

    }

    public void setBarService(BarService barService){
        this.barService = barService;
    }

    public BarService getBarService() {
        return barService;
    }
}
