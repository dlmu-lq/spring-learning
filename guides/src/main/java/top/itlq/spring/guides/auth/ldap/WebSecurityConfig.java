package top.itlq.spring.guides.auth.ldap;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

/**
 * @author liqiang
 * TODO 需要看 spring-security
 */
@Configuration
@ConditionalOnProperty(prefix = "itlq", name = "security.ldap.enable", havingValue = "true")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 拦截配置，授权
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin();
    }


    /**
     * ldap 认证管理配置 通过属性开启
     * @param auth
     * @throws Exception
     */
    @Override
    @ConditionalOnProperty(prefix = "itlq", name = "ldap.enable", havingValue = "true")
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource()
                    .url("ldap://localhost:8389/dc=springframework,dc=org")
                    .and()
                .passwordCompare()
                    .passwordEncoder(new LdapShaPasswordEncoder())
                .passwordAttribute("userPassword");
    }
}
