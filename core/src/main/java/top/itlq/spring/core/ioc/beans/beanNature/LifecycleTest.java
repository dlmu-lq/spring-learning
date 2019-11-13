package top.itlq.spring.core.ioc.beans.beanNature;

import org.springframework.context.Lifecycle;

public class LifecycleTest implements Lifecycle {
    @Override
    public void start() {
        System.out.println("lifecycle start()");
    }

    @Override
    public void stop() {
        System.out.println("lifecycle stop()");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
