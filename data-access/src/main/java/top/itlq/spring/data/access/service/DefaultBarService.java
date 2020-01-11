package top.itlq.spring.data.access.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.itlq.spring.data.access.dao.Foo;

@Service
public class DefaultBarService implements BarService{

    @Override
    public Foo get(String fooName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void get(String fooName, String barName) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional
    public void insert(Foo foo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void get(Foo foo) {
        throw new UnsupportedOperationException();
    }
}
