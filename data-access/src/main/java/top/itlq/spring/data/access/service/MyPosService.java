package top.itlq.spring.data.access.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.itlq.spring.data.access.orm.PosEntity;
import top.itlq.spring.data.access.orm.PosMapper;

import java.time.Instant;

@Service
public class MyPosService extends PosService{

    @Autowired
    public MyPosService(PosMapper posMapper) {
        super(posMapper);
    }

    /**
     * 继承了事务注解
     */
    @Override
    public void insert(){
        posMapper.insert(new PosEntity(Instant.now(), 0f, 0f));
        super.insert();
    }

    public void insertWithTransaction(){
        posMapper.insert(new PosEntity(Instant.now(), 0f, 0f));
        super.insert();
    }
}
