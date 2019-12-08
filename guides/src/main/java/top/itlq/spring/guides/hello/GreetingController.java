package top.itlq.spring.guides.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * RestController 返回对象将直接当做json被写入response @ResponseBody
 *
 */
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicInteger atomicInteger = new AtomicInteger();

    /**
     * Greeting 被当做json写入response body时，使用了在classpath中的jackson 2，MappingJackson2HttpMessageConverter,转换对象为json;
     * @param name
     * @return
     */
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(name = "name", defaultValue = "World") String name){
        return new Greeting(atomicInteger.incrementAndGet(), String.format(template, name));
    }
}
