package top.itlq.spring.data.access.service;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import top.itlq.spring.data.access.dao.UserEntity;

import java.util.List;

@Repository
public class TestDaoExceptionService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TestDaoExceptionService(BasicDataSource basicDataSource) {
        this.jdbcTemplate = new JdbcTemplate(basicDataSource);
    }

    public List<UserEntity> getUsers() {
        return jdbcTemplate.query("select * from user", new BeanPropertyRowMapper(UserEntity.class));
    }

    public List<UserEntity> getUsersWithExceptions() {
        // 测试spring封装异常用
        return jdbcTemplate.query("select * from user1", new BeanPropertyRowMapper(UserEntity.class));
    }

    public void testException() {
        // 测试spring封装异常用
        throw new IllegalArgumentException();
    }
}
