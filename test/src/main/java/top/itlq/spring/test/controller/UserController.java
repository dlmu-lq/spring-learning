package top.itlq.spring.test.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.itlq.spring.test.dao.UserEntity;

@RestController
@RequestMapping("user")
public class UserController {

    private static final Log logger = LogFactory.getLog(UserController.class);

    @Autowired
    public UserController(){

    }
    @RequestMapping("get")
    public UserEntity getUser(){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Liang");
        return userEntity;
    }
    @RequestMapping("add")
    public void addUser(UserEntity userEntity){
        logger.info("adding " + userEntity);
    }
}
