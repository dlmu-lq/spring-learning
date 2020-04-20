package top.itlq.spring.mvc.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(RootConfig.class)
public class GetRequestBodyTest {

    MockMvc mockMvc;

    @BeforeEach
    public void beforeEach(@Autowired WebApplicationContext context){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGetRequestBody() throws Exception {
        // todo 怎么会这样呢
        mockMvc.perform(
                MockMvcRequestBuilders.post("/user/print-post-body?a=b")
                        .content("b")
                        .characterEncoding(StandardCharsets.UTF_8.toString())
                        .contentType(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andExpect(content().string("b"));

        mockMvc.perform(MockMvcRequestBuilders.get("/user/print-get-body?a=b")
                .content("b").contentType(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(content().string("b"));
    }
}
