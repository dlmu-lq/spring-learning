package top.itlq.spring.guides.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 仅需要spring-guides-starter中的注解解析器，并不需要web-starter
 */
@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * FIXME fixRate 每5s运行一次，可结合fixDelay;
     *  也可使用 cron
     */
//    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime(){
        logger.info("当前时间：{}", LocalDateTime.now().format(dateTimeFormatter));
    }
}
