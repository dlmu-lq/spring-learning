package top.itlq.spring.data.access.service;

import top.itlq.spring.data.access.dao.Foo;

public interface BarService {
    void get(String fooName);
    void get(String fooName, String barName);
    void insert(Foo foo);
    void get(Foo foo);
}
