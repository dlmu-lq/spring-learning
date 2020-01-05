package top.itlq.spring.security.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationProvider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import top.itlq.spring.security.service.MyUserDetailsService;
import top.itlq.spring.security.service.WechatAuthorizationCodeTokenResponseClient;
import top.itlq.spring.security.service.WechatOAuth2UserService;

import static top.itlq.spring.security.entity.Authority.*;

/**
 * 安全自动配置 autoconfigure模块
 */
@Configuration
@EnableConfigurationProperties(WeixinSubscriptionProperties.class)
public class SecurityAutoConfiguration {

    @Bean
    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter(ClientRegistration clientRegistration){
        return new MyWebSecurityConfigurerAdapter(clientRegistration);
    }

    public static class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter{

        private ClientRegistration clientRegistration;

        public MyWebSecurityConfigurerAdapter(ClientRegistration clientRegistration){
            this.clientRegistration = clientRegistration;
        }

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
                    .withUser("lee").password("{noop}03").roles(ADMIN).and()
                    .withUser("liang").password("{noop}03").roles(USER).and().and()
                    .authenticationProvider(
                            new OAuth2LoginAuthenticationProvider(
                                    new WechatAuthorizationCodeTokenResponseClient(),
                                    new WechatOAuth2UserService()
                            )
                    );
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
            // todo 根据registrationid自动匹配注册
            ClientRegistrationRepository clientRegistrationRepository = registrationId -> clientRegistration;
            OAuth2LoginAuthenticationFilter oAuth2LoginAuthenticationFilter = new WechatOAuth2LoginAuthenticationFilter(
                    clientRegistrationRepository,
                    new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository)
            );
            oAuth2LoginAuthenticationFilter.setAuthenticationManager(authenticationManager());
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/admin/**").hasRole(ADMIN)
                    .antMatchers("/user/**").hasRole(USER)
                    .anyRequest().authenticated()
                    .and()
                    .logout().permitAll().and()
                    .formLogin().permitAll().and()
                    // oauth2登录认证拦截器
                    .addFilter(oAuth2LoginAuthenticationFilter)
                    .oauth2Login(httpSecurityOAuth2LoginConfigurer ->
                            httpSecurityOAuth2LoginConfigurer.clientRegistrationRepository(
                                    clientRegistrationRepository
                            )
                    )
                    .httpBasic();
        }
    }

    @Bean
    public ClientRegistration clientRegistration(WeixinSubscriptionProperties weixinSubscriptionProperties){
        // todo 应配置在appliaction.yml中自动注册
        return ClientRegistration.withRegistrationId("wechatSubscription")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .tokenUri("https://api.weixin.qq.com/sns/oauth2/access_token")
                .clientId(weixinSubscriptionProperties.getAppid())
                .clientSecret(weixinSubscriptionProperties.getSecret())
                .authorizationUri("https://open.weixin.qq.com/connect/qrconnect")
                .redirectUriTemplate("http://localhost:8080/login/oauth2/code/{registrationId}")
                .userInfoUri("https://api.weixin.qq.com/sns/userinfo")
                .build();
    }
}
