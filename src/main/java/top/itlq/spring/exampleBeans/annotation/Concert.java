package top.itlq.spring.exampleBeans.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Concert {
    @Autowired
    @Qualifier("a")
    public Music music1;
}
