package top.itlq.spring.cloud.consumer.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.itlq.spring.cloud.interfaces.entity.UserEntity;
import top.itlq.spring.cloud.interfaces.service.UserService;

@RestController
@RequestMapping
public class UserController {
    @Reference
    UserService userService;

    @RequestMapping("user")
    public UserEntity user(@RequestParam Long uid){
        return userService.getUser(uid);
    }

}
