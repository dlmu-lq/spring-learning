package top.itlq.spring.guides.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RegisterControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void register() throws Exception {
        UserEntity userEntity = new UserEntity();
        mockMvc.perform(post("/validator/register.do")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userEntity)))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("name 长度限制：2-30"));
        userEntity.setName("t");
        mockMvc.perform(post("/validator/register.do")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userEntity)))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("name 长度限制：2-30"));
        userEntity.setName("tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt");
        mockMvc.perform(post("/validator/register.do")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userEntity)))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("name 长度限制：2-30"));
        userEntity.setName("test");
        mockMvc.perform(post("/validator/register.do")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userEntity)))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("age 必须大于或等于18"));
        userEntity.setAge(17);
        mockMvc.perform(post("/validator/register.do")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userEntity)))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("age 必须大于或等于18"));
        userEntity.setAge(18);
        mockMvc.perform(post("/validator/register.do")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userEntity)))
                .andExpect(status().isOk())
                .andExpect(content().string("success"));
    }
}