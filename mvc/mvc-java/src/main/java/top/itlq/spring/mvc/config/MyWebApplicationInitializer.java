package top.itlq.spring.mvc.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * 使用了MyWebAppInitializer配置
 * 自动被servlet 容器检测到， 无需spring配置等
 * 可用来代替 web.xml 配置 web 应用
 * spring boot不同，不将其与servlet容器的连接交给开发者，而是使用spring配置引导自己和内嵌的servlet容器
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        // 可在此配置 spring context，dispatcherServlet；
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(RootConfig.class);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic registration = servletContext.addServlet("app", dispatcherServlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/app/*");
    }
}