package top.itlq.spring.data.access.service.concurrent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.itlq.spring.data.access.orm.KeyEntity;
import top.itlq.spring.data.access.orm.KeyMapper;

@Service
public class KeyService {

    private final KeyMapper keyMapper;

    @Autowired
    public KeyService(KeyMapper keyMapper){
        this.keyMapper = keyMapper;
    }

    @Transactional
    public Long getAndUpdateNumber(String id){
        // 此处使用了数据库的行级锁，一个事务正在操作该行时，其他事务只能等待（直接获得排他锁）；
        Long re = keyMapper.loadForUpdate(id).getNumber();
        keyMapper.update(new KeyEntity(id, re + 1));
        return re;
    }
}
