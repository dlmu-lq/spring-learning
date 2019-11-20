//package top.itlq.spring.mvc.learning.config;
//
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
///**
// * 相比 WebApplicationInitializer 包含更多默认实现，创建 DispatcherServlet等操作，
// */
//public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[]{RootConfig.class};
//    }
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class[]{App1Config.class};
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/app/*"};
//    }
//}
