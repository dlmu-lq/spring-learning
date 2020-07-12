package top.itlq.spring.cloud.interfaces.service;

import top.itlq.spring.cloud.interfaces.entity.UserEntity;

public interface UserService {
    UserEntity getUser(Long id);
}
