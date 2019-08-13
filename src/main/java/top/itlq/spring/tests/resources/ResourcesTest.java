package top.itlq.spring.tests.resources;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.*;
import top.itlq.spring.resources.MessengerService;
import top.itlq.spring.resources.TestBean1;
import top.itlq.spring.resources.TestBean2;

import java.io.InputStream;
import java.util.Arrays;

/**
 * 测试Resources
 */
class ResourcesTest {
    /**
     * 通过context.getResource() 读取Resource
     */
    @Test
    void testResourceType(){
        // 根据context类型决定Resource类型
        ApplicationContext context = new ClassPathXmlApplicationContext();
        Resource resource = context.getResource("resources/a.txt");
        Assertions.assertTrue(resource instanceof ClassPathResource);
        Assertions.assertTrue(resource.exists());
        Assertions.assertEquals(readResource(resource), "哈哈");
        context = new FileSystemXmlApplicationContext();
        resource = context.getResource("resources/a.txt");
        Assertions.assertTrue(resource instanceof FileSystemResource);
        Assertions.assertFalse(resource.exists());
        // 通过前缀可以强制
        resource = context.getResource("classpath:resources/a.txt");
        Assertions.assertTrue(resource instanceof ClassPathResource);
        Assertions.assertTrue(resource.exists());
        // 前缀强于context类型
        // 注意file前缀的file:///表示文件协议，后面加计算机文件目录地址，表示 URL 对应文件系统文件位置
        resource = context.getResource("file:///resources/a.txt");
        Assertions.assertTrue(resource instanceof UrlResource);
        Assertions.assertTrue(context.getResource("http:///resources/a.txt") instanceof UrlResource);
    }

    @Test
    void testResourceLoaderAware(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    }

    @Test
    void testClassPathXmlApplicationShortcuts(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"test1.xml","test2.xml"}, MessengerService.class);
        Assertions.assertNotNull(context.getBean(TestBean1.class));
        Assertions.assertNotNull(context.getBean(TestBean2.class));
    }

    private static String readResource(Resource resource){
        try(InputStream inputStream = resource.getInputStream()) {
            return new String(inputStream.readAllBytes());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
