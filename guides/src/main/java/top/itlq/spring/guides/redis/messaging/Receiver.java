package top.itlq.spring.guides.redis.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * 消息接收者
 */
public class Receiver {
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch countDownLatch;

    @Autowired
    public Receiver(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    public void receiveMessage(String message){
        logger.info("Received Message:" + message);
        countDownLatch.countDown();
    }
}
