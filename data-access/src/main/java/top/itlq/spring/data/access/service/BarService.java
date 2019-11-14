package top.itlq.spring.data.access.service;

import org.springframework.transaction.annotation.Transactional;
import top.itlq.spring.data.access.dao.Foo;

@Transactional // todo 据说定义在接口上，在代理使用 cglib 时，不好用，但验证是好用的（事务回滚了）
public interface BarService {
    void get(String fooName);
    void get(String fooName, String barName);
    void insert(Foo foo);
    void get(Foo foo);
}
