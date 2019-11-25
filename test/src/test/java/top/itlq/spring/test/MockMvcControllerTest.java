package top.itlq.spring.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import top.itlq.spring.test.controller.UserController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * 直接使用 controller 配置，没有mvc配置，todo 测试也通过，HttpMessageConverter 从哪儿来？
 */
public class MockMvcControllerTest {
    MockMvc mockMvc;
    @BeforeEach
    void beforeEach(){
        mockMvc = standaloneSetup(new UserController()).build();
    }

    @Test
    void test() throws Exception {
        mockMvc.perform(get("/user/get"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name").value("Liang"));
    }
}

