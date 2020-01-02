package top.itlq.spring.configure.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityTestControllerTest {

    @Autowired
    MockMvc mockMvc;

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
}