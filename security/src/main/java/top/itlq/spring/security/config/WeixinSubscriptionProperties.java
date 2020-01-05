package top.itlq.spring.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 微信公众号相关配置
 */
@ConfigurationProperties("itlq.weixin.subscription")
public class WeixinSubscriptionProperties {

    private String appid;

    private String secret;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
