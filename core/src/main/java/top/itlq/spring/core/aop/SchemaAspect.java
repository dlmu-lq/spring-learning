package top.itlq.spring.core.aop;


import org.aspectj.lang.ProceedingJoinPoint;

public class SchemaAspect {
    public void testBefore(){
        System.out.println("aop before...");
    }

    //    如果不用aspect织入，这个around方法不好用
    public Object testAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("aop around before...");
        pjp.proceed();
        System.out.println("aop around after...");
        return new Object();
    }
    public void testAfter(){
        System.out.println("aop after...");
    }
}
