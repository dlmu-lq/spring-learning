package top.itlq.spring.data.access.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.itlq.spring.data.access.orm.PosEntity;
import top.itlq.spring.data.access.orm.PosMapper;

import java.time.Instant;

@Service
public class PosService {

    protected final PosMapper posMapper;

    @Autowired
    public PosService(PosMapper posMapper){
        this.posMapper = posMapper;
    }

    /**
     * 外层有事务时，此事务新建
     */
    @Transactional
    public void insert(){
        posMapper.insert(new PosEntity(Instant.now(), 1f, 1f));
        System.out.println(1 / 0);
        posMapper.insert(new PosEntity(Instant.now(), 2f, 2f));
    }

    /**
     * 外层有事务时，此事务新建
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertWithNewTransaction(){
        posMapper.insert(new PosEntity(Instant.now(), 1f, 1f));
        posMapper.insert(new PosEntity(Instant.now(), 1f, 1f));
    }
}
