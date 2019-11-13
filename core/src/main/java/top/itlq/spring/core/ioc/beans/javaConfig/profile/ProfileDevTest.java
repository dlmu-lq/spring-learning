package top.itlq.spring.core.ioc.beans.javaConfig.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class ProfileDevTest implements ProfileTest {
}
