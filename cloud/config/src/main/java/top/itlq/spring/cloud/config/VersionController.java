package top.itlq.spring.cloud.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用Bean生命周期解决配置数据更新问题
 */
@RestController
@RefreshScope
public class VersionController {

    @Value("${itlq.spring.version}")
    String version;

    @RequestMapping("/version")
    public String version(){
        System.out.println(this);
        return version;
    }
}
