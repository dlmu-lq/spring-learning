package top.itlq.spring.guides.redis.messaging;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
@TestPropertySource(properties = "itlq.redis.enable=true")
class RedisConfigurationTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    CountDownLatch countDownLatch;

    @Test
    void testRedisMessaging() throws InterruptedException {
        stringRedisTemplate.convertAndSend("chat", "Hello from Redis test!");
        countDownLatch.await();
        System.exit(0);
    }
}