package top.itlq.spring.ioc.beans.beanNature;

import org.springframework.context.LifecycleProcessor;

public class LifecycleProcessorTest implements LifecycleProcessor {
    @Override
    public void onRefresh() {
        System.out.println("lifecycleProcessor onRefresh()");
    }

    @Override
    public void onClose() {
        System.out.println("lifecycleProcessor onClose()");
    }

    @Override
    public void start() {
        System.out.println("lifecycleProcessor start()");
    }

    @Override
    public void stop() {
        System.out.println("lifecycleProcessor stop()");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
