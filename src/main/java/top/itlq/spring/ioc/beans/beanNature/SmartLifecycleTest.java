package top.itlq.spring.ioc.beans.beanNature;

import org.springframework.context.SmartLifecycle;

public class SmartLifecycleTest implements SmartLifecycle {
    @Override
    public void start() {
        System.out.println("smartLifecycle start()");
    }

    @Override
    public void stop() {
        System.out.println("smartLifecycle stop()");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
