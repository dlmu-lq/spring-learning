package top.itlq.spring.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;
import top.itlq.spring.test.dao.UserDao;
import top.itlq.spring.test.service.user.UserService;

@SpringJUnitWebConfig(classes = TestConfig.class)
public class WebAppTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    MockServletContext mockServletContext;

    @Autowired
    MockHttpSession httpSession;

    @Autowired
    MockHttpServletRequest request;

    @Autowired
    MockHttpServletResponse response;

    @Autowired
    ServletWebRequest servletWebRequest;

    @Autowired
    UserService userService;

    @Test
    void testRequest(){
        String testParam = "aa";
        request.setParameter("test",testParam);
        Assertions.assertEquals(userService.testRequestParam(), testParam);
    }

    @Test
    void testSession(){
        String testAttr = "bb";
        httpSession.setAttribute("test",testAttr);
        Assertions.assertEquals(userService.testSessionAttr(), testAttr);
    }
}
