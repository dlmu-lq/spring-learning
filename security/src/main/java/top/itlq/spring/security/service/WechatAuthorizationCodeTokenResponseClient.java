package top.itlq.spring.security.service;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthorizationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import top.itlq.spring.security.entity.WechatTokenResponse;

import java.util.Collections;
import java.util.HashMap;

/**
 * 微信公众号通过code获取access_token及结果处理的接口实现
 * @see org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient
 */
public class WechatAuthorizationCodeTokenResponseClient implements OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {
    private static final String INVALID_TOKEN_RESPONSE_ERROR_CODE = "invalid_token_response";

    private Converter<OAuth2AuthorizationCodeGrantRequest, RequestEntity<?>> requestEntityConverter =
            new WechatOAuth2AuthorizationCodeGrantRequestEntityConverter();

    private RestOperations restOperations;

    public WechatAuthorizationCodeTokenResponseClient() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_PLAIN));
        mappingJackson2HttpMessageConverter.getObjectMapper().setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
        this.restOperations = restTemplate;
    }

    @Override
    public OAuth2AccessTokenResponse getTokenResponse(OAuth2AuthorizationCodeGrantRequest authorizationCodeGrantRequest) {
        Assert.notNull(authorizationCodeGrantRequest, "authorizationCodeGrantRequest cannot be null");

        RequestEntity<?> request = this.requestEntityConverter.convert(authorizationCodeGrantRequest);

        ResponseEntity<WechatTokenResponse> response;
        try {
            response = this.restOperations.exchange(request, WechatTokenResponse.class);
        } catch (RestClientException ex) {
            OAuth2Error oauth2Error = new OAuth2Error(INVALID_TOKEN_RESPONSE_ERROR_CODE,
                    "An error occurred while attempting to retrieve the OAuth 2.0 Access Token Response: " + ex.getMessage(), null);
            throw new OAuth2AuthorizationException(oauth2Error, ex);
        }

        WechatTokenResponse wechatTokenResponse = response.getBody();

        OAuth2AccessTokenResponse tokenResponse = OAuth2AccessTokenResponse
                .withToken(wechatTokenResponse.getAccessToken())
                .refreshToken(wechatTokenResponse.getRefreshToken())
                .expiresIn(wechatTokenResponse.getExpiresIn())
                .tokenType(OAuth2AccessToken.TokenType.BEARER) // todo don't know what this means;
                .additionalParameters(new HashMap<String,Object>() {{
                    put("openid", wechatTokenResponse.getOpenid());
                }})
                .build();

        if (!StringUtils.isEmpty(wechatTokenResponse.getScope())) {
            // As per spec, in Section 5.1 Successful Access Token Response
            // https://tools.ietf.org/html/rfc6749#section-5.1
            // If AccessTokenResponse.scope is empty, then default to the scope
            // originally requested by the client in the Token Request
            tokenResponse = OAuth2AccessTokenResponse.withResponse(tokenResponse)
                    .scopes(authorizationCodeGrantRequest.getClientRegistration().getScopes())
                    .build();
        }

        return tokenResponse;
    }
}
