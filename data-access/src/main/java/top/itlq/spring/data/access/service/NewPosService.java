package top.itlq.spring.data.access.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.itlq.spring.data.access.orm.PosEntity;
import top.itlq.spring.data.access.orm.PosMapper;

import java.time.Instant;

@Service
public class NewPosService{

    private final PosMapper posMapper;
    private final PosService posService;

    @Autowired
    public NewPosService(PosMapper posMapper, PosService posService) {
        this.posMapper = posMapper;
        this.posService = posService;
    }

    @Transactional
    public void insertWith2Transaction(){
        posMapper.insert(new PosEntity(Instant.now(), 0f, 0f));
        // 此处开启了新事务，不会回滚其内部的
        posService.insertWithNewTransaction();
        posMapper.insert(new PosEntity(Instant.now(), 0f, 0f));
        System.out.println(1 / 0);
    }
}
