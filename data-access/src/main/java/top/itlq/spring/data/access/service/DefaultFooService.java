package top.itlq.spring.data.access.service;

import org.springframework.stereotype.Service;
import top.itlq.spring.data.access.dao.Foo;

@Service
public class DefaultFooService implements FooService{

    @Override
    public void get(String fooName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void get(String fooName, String barName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insert(Foo foo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void get(Foo foo) {
        throw new UnsupportedOperationException();
    }
}
