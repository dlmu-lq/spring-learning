package top.itlq.spring.exampleBeans.enviroment;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DevTest {
}
