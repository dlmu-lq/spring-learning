package top.itlq.spring.cloud.provider.service;

import org.apache.dubbo.config.annotation.Service;
import top.itlq.spring.cloud.interfaces.entity.UserEntity;
import top.itlq.spring.cloud.interfaces.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserEntity getUser(Long id) {
        return new UserEntity(1L, "test", "test");
    }
}
