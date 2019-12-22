package top.itlq.spring.configure.mybatis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.itlq.spring.configure.orm.User;
import top.itlq.spring.configure.orm.UserMapper;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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