package top.itlq.spring.boot.mybatis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.itlq.spring.boot.orm.User;
import top.itlq.spring.boot.orm.UserMapper;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisAutoConfigurationTest {
    @Autowired
    UserMapper userMapper;
    @Test
    void testMybatis(){
        List<User> userList = userMapper.selectUser(new HashMap<>());
        System.out.println(userList);
    }
}