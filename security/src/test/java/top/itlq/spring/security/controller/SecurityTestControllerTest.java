package top.itlq.spring.security.controller;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import top.itlq.spring.security.config.WeixinSubscriptionProperties;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

class SecurityTestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WeixinSubscriptionProperties weixinSubscriptionProperties;

    static MockHttpSession mockHttpSession;

    @Test
    void openTest() throws Exception {
        mockMvc.perform(post("/open/test.do")).andExpect(status().isOk());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    void userTest() throws Exception {
        mockHttpSession = new MockHttpSession();
        mockMvc.perform(
                post("/login")
                        .session(mockHttpSession)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "lee")
                        .param("password", "03")
        ).andExpect(status().is3xxRedirection());
        mockMvc.perform(post("/user/test.do").session(mockHttpSession)).andExpect(status().is4xxClientError());
        mockHttpSession = new MockHttpSession();
        mockMvc.perform(
                post("/login").session(mockHttpSession)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "liang")
                        .param("password", "03")
        ).andExpect(status().is3xxRedirection());
        mockMvc.perform(post("/user/test.do").session(mockHttpSession)).andExpect(status().is2xxSuccessful());
    }

    @Test
    void adminTest() throws Exception {
        mockHttpSession = new MockHttpSession();
        mockMvc.perform(
                post("/login")
                        .session(mockHttpSession)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "liang")
                        .param("password", "03")
        ).andExpect(status().is3xxRedirection());
        mockMvc.perform(post("/admin/test.do").session(mockHttpSession)).andExpect(status().is4xxClientError());
        mockHttpSession = new MockHttpSession();
        mockMvc.perform(
                post("/login")
                        .session(mockHttpSession)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "lee")
                        .param("password", "03")
        ).andExpect(status().is3xxRedirection());
        mockMvc.perform(post("/admin/test.do").session(mockHttpSession)).andExpect(status().is2xxSuccessful());
    }

    @Test
    void testRestTemplate(){
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_PLAIN));
        mappingJackson2HttpMessageConverter.getObjectMapper().setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        final MediaType contentType = MediaType.valueOf(APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        headers.setContentType(contentType);
//                MultiValueMap<String, String> formParameters = this.buildFormParameters(authorizationCodeGrantRequest);
        MultiValueMap<String, String> formParameters = new LinkedMultiValueMap<>();
        formParameters.add(OAuth2ParameterNames.GRANT_TYPE, AuthorizationGrantType.AUTHORIZATION_CODE.getValue());
        formParameters.add(OAuth2ParameterNames.CODE, "021RyzTS1ovbB21qqOUS1HzyTS1RyzTt");
        formParameters.add("appid", weixinSubscriptionProperties.getAppid());
        formParameters.add("secret", weixinSubscriptionProperties.getSecret());
        URI uri = UriComponentsBuilder.fromUriString("https://api.weixin.qq.com/sns/oauth2/access_token")
                .build()
                .toUri();

        RequestEntity requestEntity = new RequestEntity<>(formParameters, headers, HttpMethod.POST, uri);
        ResponseEntity responseEntity = restTemplate.exchange(requestEntity,AccessTokenResult.class);
        System.out.println(responseEntity.getBody());
    }

    static class AccessTokenResult{
        public String accessToken;
        public String errcode;
        public String errmsg;
    }
}