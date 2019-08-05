package top.itlq.spring.exampleBeans.enviroment;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("pro")
@Component
public class ProTest {
}
