package top.itlq.spring.data.access.service;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import top.itlq.spring.data.access.dao.UserEntity;
import top.itlq.spring.data.access.orm.PosEntity;
import top.itlq.spring.data.access.orm.PosMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class TestDaoExceptionService {

    private final JdbcTemplate jdbcTemplate;
    private final PosMapper posMapper;

    @Autowired(required = false)
    public TestDaoExceptionService(BasicDataSource basicDataSource, PosMapper posMapper) {
        this.jdbcTemplate = new JdbcTemplate(basicDataSource);
        this.posMapper = posMapper;
    }

    public TestDaoExceptionService(BasicDataSource basicDataSource) {
        this.jdbcTemplate = new JdbcTemplate(basicDataSource);
        this.posMapper = null;
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

    // 也可以拦截，在SqlSessionTemplate中拦截
    public List<PosEntity> testMybatisException(){
        return posMapper.testException();
    }
}
