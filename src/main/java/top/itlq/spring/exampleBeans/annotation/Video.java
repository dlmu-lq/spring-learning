package top.itlq.spring.exampleBeans.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class Video {

//    @Resource(name = "person") // 一旦指定name，只匹配name
    @Resource //不指定name，匹配签名
    // 如果使用已存在的bean name，但类型不匹配即抛错(即使没有指定name，先匹配签名name)
//    public Person event2;
    public Person aaa;

    @Autowired
    public Video(Event event2){
        System.out.println("Video event:" + event2);
    }

//    @Autowired
//    public Video(Event event){
//        System.out.println("Video event:" + event);
//    }
}
