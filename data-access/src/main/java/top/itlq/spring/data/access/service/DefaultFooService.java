package top.itlq.spring.data.access.service;

import top.itlq.spring.data.access.dao.Foo;

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
