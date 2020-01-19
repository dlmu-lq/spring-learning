package top.itlq.spring.data.access.orm;

import org.apache.ibatis.annotations.Param;

public interface KeyMapper {
    KeyEntity loadForUpdate(@Param("id") String id);
    void update(KeyEntity keyEntity);
}
