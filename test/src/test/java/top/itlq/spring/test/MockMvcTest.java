package top.itlq.spring.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

import org.springframework.web.context.WebApplicationContext;

@SpringJUnitWebConfig(TestWebappConfig.class)
public class MockMvcTest {

    MockMvc mockMvc;

    @BeforeEach
    void beforeEach(@Autowired WebApplicationContext wac){
        mockMvc = webAppContextSetup(wac).apply(sharedHttpSession()).build();
    }

    @Test
    void test() throws Exception {
        mockMvc.perform(get("/user/get"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name").value("Liang"));
    }
}
