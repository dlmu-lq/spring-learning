package top.itlq.spring.security.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationExchange;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 拦截进行微信公众号oauth2登录认证授权
 * @see OAuth2LoginAuthenticationFilter
 * 参考并进行自定义（因为微信公众号类似oauth2，但authenticationRequest相关的流程由只能由微信控制而不是这儿的后台）
 */
public class WechatOAuth2LoginAuthenticationFilter extends OAuth2LoginAuthenticationFilter {

    private ClientRegistrationRepository childClientRegistrationRepository;
    /**
     * 模拟的oauth2流程中的authenticationRequest
     */
    private OAuth2AuthorizationRequest mockAuthenticationRequest;

    public WechatOAuth2LoginAuthenticationFilter(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientService authorizedClientService) {
        super(clientRegistrationRepository, authorizedClientService);
        String registrationId = "wechatSubscription";
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(registrationId);
        this.childClientRegistrationRepository = clientRegistrationRepository;
        mockAuthenticationRequest =
                OAuth2AuthorizationRequest.authorizationCode()
                        .authorizationUri(clientRegistration.getProviderDetails().getAuthorizationUri())
                        .clientId(clientRegistration.getClientId())
                        .redirectUri(clientRegistration.getRedirectUriTemplate().replace("{registrationId}", registrationId))
                        .state("STATE")
                        .build();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        MultiValueMap<String, String> params = toMultiMap(request.getParameterMap());
        String redirectUri = UriComponentsBuilder.fromHttpUrl(UrlUtils.buildFullRequestUrl(request))
                .replaceQuery(null)
                .build()
                .toUriString();
        OAuth2AuthorizationResponse authorizationResponse = convert(params, redirectUri);

        Object authenticationDetails = this.authenticationDetailsSource.buildDetails(request);

        ClientRegistration clientRegistration = this.childClientRegistrationRepository.findByRegistrationId(null);
        OAuth2LoginAuthenticationToken authenticationRequest = new OAuth2LoginAuthenticationToken(
                clientRegistration, new OAuth2AuthorizationExchange(mockAuthenticationRequest, authorizationResponse));
        authenticationRequest.setDetails(authenticationDetails);

        OAuth2LoginAuthenticationToken authenticationResult =
                (OAuth2LoginAuthenticationToken) this.getAuthenticationManager().authenticate(authenticationRequest);

        OAuth2AuthenticationToken oauth2Authentication = new OAuth2AuthenticationToken(
                authenticationResult.getPrincipal(),
                authenticationResult.getAuthorities(),
                authenticationResult.getClientRegistration().getRegistrationId());
        oauth2Authentication.setDetails(authenticationDetails);

        return oauth2Authentication;
    }

    static MultiValueMap<String, String> toMultiMap(Map<String, String[]> map) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>(map.size());
        map.forEach((key, values) -> {
            if (values.length > 0) {
                for (String value : values) {
                    params.add(key, value);
                }
            }
        });
        return params;
    }

    static OAuth2AuthorizationResponse convert(MultiValueMap<String, String> request, String redirectUri) {
        String code = request.getFirst(OAuth2ParameterNames.CODE);
        String errorCode = request.getFirst(OAuth2ParameterNames.ERROR);
        String state = request.getFirst(OAuth2ParameterNames.STATE);

        if (StringUtils.hasText(code)) {
            return OAuth2AuthorizationResponse.success(code)
                    .redirectUri(redirectUri)
                    .state(state)
                    .build();
        } else {
            String errorDescription = request.getFirst(OAuth2ParameterNames.ERROR_DESCRIPTION);
            String errorUri = request.getFirst(OAuth2ParameterNames.ERROR_URI);
            return OAuth2AuthorizationResponse.error(errorCode)
                    .redirectUri(redirectUri)
                    .errorDescription(errorDescription)
                    .errorUri(errorUri)
                    .state(state)
                    .build();
        }
    }
}
