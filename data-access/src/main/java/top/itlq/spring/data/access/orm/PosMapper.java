package top.itlq.spring.data.access.orm;

import java.util.List;

public interface PosMapper {
    List<PosEntity> testInterval();
    void insert(PosEntity entity);
}
