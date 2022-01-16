package top.itlq.spring.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(ConfigApplication.class, args);
//        while (true){
//            TimeUnit.SECONDS.sleep(5);
            String property = configurableApplicationContext.getEnvironment().getProperty("itlq.spring.version");
            System.out.println("nacos-config property: " + property);
            String property2 = configurableApplicationContext.getEnvironment().getProperty("itlq.spring.p");
            System.out.println("itlq.spring.p: " + property2);
//        }
    }
}
