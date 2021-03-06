package top.itlq.spring.guides;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 */

/**
 * Fixme @SpringBootApplication 注解包括含义：
 *      @Configuration 用来给应用上下文做bean定义；
 *      @EnableAutoConfiguration 根据类路径上的设置，其他beans，不同的property设置开始添加beans，
 *          例如，如果spring-webmvc在类路径上，将标记这个应用为web应用，开始一些行为，如创建DispatcherServlet；
 *      @ComponentScan 在这个路径及以下寻找其他components，configuration，services，找到Controller；
 */
@SpringBootApplication
/**
 * Fixme EnableScheduling 开启定时任务运行；创建背后的任务执行器 task executor
 */
@EnableScheduling
public class Application {
    public static void main(String...args){
        SpringApplication.run(Application.class, args);
    }
}
