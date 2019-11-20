package top.itlq.spring.mvc.converters;

import org.springframework.core.convert.converter.Converter;
import top.itlq.spring.mvc.learning.beans.TestBean;

public class MyConverter implements Converter<TestBean,Integer> {

    public MyConverter(){
        System.out.println("myConverter inited");
    }

    @Override
    public Integer convert(TestBean source) {
        return null;
    }
}
