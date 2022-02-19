package top.itlq.spring.core.aop;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 */
public class TestCGLIB {
    void test(){
        System.out.println("test");
    }
    final void test2(){
        System.out.println("test2");
    }
    static void test3(){
        System.out.println("test3");
    }
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TestCGLIB.class);
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println(("before " + method));
                Object re = proxy.invokeSuper(obj, args);
                System.out.println(("after " + method));
                return re;
            }
        });
        TestCGLIB sub = (TestCGLIB) enhancer.create();
        System.out.println(sub);
        sub.test();
        sub.test2();
        sub.test3();
        Proxy.newProxyInstance(TestCGLIB.class.getClassLoader(), new Class[]{IService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
    }
}
