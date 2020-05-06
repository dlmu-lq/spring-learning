package top.itlq.spring.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AnnotationAspect {

    @Pointcut("execution(* *..aop..test1(..))")
    public void aopBefore(){
    }

    @Before("aopBefore()")
    public void before(){
        System.out.println("before...");
    }

    @Around("execution(* test2(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before...");
        Object o = pjp.proceed();
        System.out.println("around after...");
        return o;
    }

    @After("execution(* test3(..))")
    public void after(){
        System.out.println("after...");
    }
}
