package top.itlq.spring.data.access.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.itlq.spring.data.access.config.TransactionConfiguration;
import top.itlq.spring.data.access.service.concurrent.KeyService;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试事务和数据库行锁结合控制的并发
 */
public class TestConcurrent {

    private AnnotationConfigApplicationContext context;

    @BeforeEach
    void createContext(){
        context = new AnnotationConfigApplicationContext(TransactionConfiguration.class);
    }

    /**
     * 测试方法，并行调用，检查最终结果
     */
    @Test
    void test(){
        KeyService keyService = context.getBean(KeyService.class);
        Long id0InitNum = keyService.getAndUpdateNumber("id0"),
                id1InitNum = keyService.getAndUpdateNumber("id1");
        int threadSize = 50, updateSize = 50; // 线程数，每个线程循环更新次数；
        CountDownLatch countDownLatch = new CountDownLatch(1),
                countDownLatch1 = new CountDownLatch(threadSize * 2);
        for(int i=0;i<threadSize*2;i++){
            // 同时更新两行；
            final int idNum = i % 2;
            new Thread(()->{
                try {
                    countDownLatch.await();
                    String id = "id" + (idNum % 2);
                    // 每个线程循环更新多次
                    keyService.getAndUpdateNumber(id, updateSize);
                    countDownLatch1.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        countDownLatch.countDown();
        try {
            countDownLatch1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long id0EndNum = keyService.getAndUpdateNumber("id0"),
                id1EndNum = keyService.getAndUpdateNumber("id1");
        assertEquals(id0EndNum.longValue(), id0InitNum + threadSize * updateSize + 1);
        assertEquals(id1EndNum.longValue(), id1InitNum + threadSize * updateSize + 1);
    }
}
