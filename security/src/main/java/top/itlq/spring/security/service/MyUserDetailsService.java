package top.itlq.spring.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import top.itlq.spring.security.entity.Authority;
import top.itlq.spring.security.entity.User;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static top.itlq.spring.security.entity.Authority.*;

public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        /**
         * 模拟给权限
         */
        switch (username){
            case "admin":
                user.setAuthorities(Stream.of(USER, ADMIN).map(Authority::new).collect(Collectors.toList()));
            default:
                user.setAuthorities(Collections.singleton(new Authority(USER)));
        }
        return user;
    }
}
