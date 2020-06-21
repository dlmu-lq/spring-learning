package top.itlq.spring.cloud.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 通过LoadBalancerClient获得服务提供者实例，通过RestTemplate访问团它
 */
@RestController
public class ConsumerController{

    // spring 完成了自动配置，直接注入，注入的是 RibbonLoadBalancerClient
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @Value("${spring.application.name}")
    String applicationName;

    @GetMapping("/echo/app-name")
    public String echoAppName(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
        String requestUrl = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), applicationName);
        return restTemplate.getForObject(requestUrl, String.class);
    }
}