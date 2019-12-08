package top.itlq.spring.guides.auth.memory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author liqiang
 * TODO 需要看 spring-security
 */
@Configuration
@ConditionalOnProperty(prefix = "itlq", name = "security.memory.enable", havingValue = "true")
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
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin();
    }

    /**
     * 内存中保存的用户
     * @return
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("guest")
                .password("password")
                .roles()
                .build();
        return new InMemoryUserDetailsManager(user, user2);
    }
}
