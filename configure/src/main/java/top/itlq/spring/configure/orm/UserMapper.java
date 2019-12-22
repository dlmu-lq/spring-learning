package top.itlq.spring.configure.orm;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> selectUser(Map<String, Object> params);
    void insert(User user);
}
