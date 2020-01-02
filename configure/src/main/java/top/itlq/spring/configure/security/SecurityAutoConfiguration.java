package top.itlq.spring.configure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static top.itlq.spring.configure.security.Authority.*;

/**
 * 安全自动配置 autoconfigure模块
 * @see org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration 自动配置主配置类；
 * @see org.springframework.security.authentication.DefaultAuthenticationEventPublisher 身份认证消息发布器；
 * @see org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration 用来默认开启security，可以配置WebSecurityConfigurer或WebSecurityConfigurerAdapter
 * @see org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration 配置默认WebSecurityConfigurer即WebSecurityConfigurerAdapter
 *
 * 配置运行机制，原理
 *
 * 外部容器
 *      @see org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer webSecurity被容器检测到运行的入口，配置filter
 *      @see org.springframework.web.filter.DelegatingFilterProxy 容器servletContext注册的filter；
 * 内嵌容器
 *      @see org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration
 *      @see org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean
 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration 配置WebSecurity创建springSecurityFilterChain，即DelegatingFilterProxy内部的真正filter；
 * @see org.springframework.security.config.annotation.web.builders.WebSecurity 用来build出最后的springSecurityFilterChain
 *      利用一系列 securityFilterChainBuilders build  最终创建springSecurityFilterChain
 *      https://www.iteye.com/blog/fengyilin-2409220
 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
 *      WebSecurity用来build的默认SecurityConfigurer，一般继承它实现自定义操作；
 *      向WebSecurity默认添加HttpSecurity，并提供配置HttpSecurity的接口；
 * @see org.springframework.security.config.annotation.web.builders.HttpSecurity
 *      利用一系列 SecurityConfigurer 添加一系列filter，
 *      作为一个securityFilterChainBuilder用所添加的filters默认给WebSecurity来build一个SecurityFilterChain最终加入到springSecurityFilterChain中
 */
@Configuration
public class SecurityAutoConfiguration {

    @Bean
    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter(){
        return new MyWebSecurityConfigurerAdapter();
    }

    public static class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter{
        /**
         * 认证信息中的GrantedAuthority从配置的userDetailsService的loadUserByName方法返回的UserDetails中取；
          * @param auth
         * @throws Exception
         * @see GrantedAuthority
         */
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .userDetailsService(new MyUserDetailsService()).passwordEncoder(new BCryptPasswordEncoder())
                    .and()
                    .inMemoryAuthentication()
                    .withUser("lee").password("{noop}03").roles(ADMIN)
                        .and()
                    .withUser("liang").password("{noop}03").roles(USER);
        }

        /**
         * 忽略的请求
         * @param web
         * @throws Exception
         */
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/open/**/*");
        }

        /**
         * 不同url对应不同认证信息角色
         * @param http
         * @throws Exception
         */
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/admin/**").hasRole(ADMIN)
                    .antMatchers("/user/**").hasRole(USER)
                    .anyRequest().authenticated()
                    .and()
                    .logout().permitAll().and()
                    .formLogin().permitAll().and()
                    .httpBasic();
        }
    }
}
