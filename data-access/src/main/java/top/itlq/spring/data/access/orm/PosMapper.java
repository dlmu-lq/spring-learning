package top.itlq.spring.data.access.orm;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PosMapper {
    List<PosEntity> testInterval();
    void insert(PosEntity entity);
    // 测试异常
    @Select("select * from pos1")
    List<PosEntity> testException();
}
