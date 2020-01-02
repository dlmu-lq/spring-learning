package top.itlq.spring.configure.security;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    private String key;

    public Authority(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getAuthority() {
        return key;
    }
}
