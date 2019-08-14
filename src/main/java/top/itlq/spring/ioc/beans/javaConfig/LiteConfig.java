package top.itlq.spring.ioc.beans.javaConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author liqiang
 */
@Component
public class LiteConfig {

    private LiteDependency liteDependency;

    @Autowired
    public LiteConfig(LiteDependency liteDependency){
        this.liteDependency = liteDependency;
    }

    public LiteDependency getLiteDependency() {
        return liteDependency;
    }

    public void setLiteDependency(LiteDependency liteDependency) {
        this.liteDependency = liteDependency;
    }

    @Bean
    public Chocolate chocolate(){
        return new Chocolate();
    }

    @Bean(initMethod = "init",destroyMethod = "")
    public IceCream iceCream(){
        // 普通方法语义调用，不能直接使用bean依赖；
        return new IceCream(chocolate());
    }
}
