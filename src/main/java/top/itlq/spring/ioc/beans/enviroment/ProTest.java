package top.itlq.spring.ioc.beans.enviroment;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("pro")
@Component
public class ProTest {
}
