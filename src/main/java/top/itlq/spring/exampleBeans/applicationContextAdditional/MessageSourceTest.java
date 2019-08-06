package top.itlq.spring.exampleBeans.applicationContextAdditional;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

@Component
@ImportResource("/message-source-test.xml") // 导入messageSource
public class MessageSourceTest implements MessageSourceAware {
    @Override
    public void setMessageSource(MessageSource messageSource) {
        System.out.println(messageSource.getMessage("testMessage", null, null));
    }
}
