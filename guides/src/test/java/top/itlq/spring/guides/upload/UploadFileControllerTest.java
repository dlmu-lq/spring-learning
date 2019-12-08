package top.itlq.spring.guides.upload;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UploadFileControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void testUpload() throws Exception {
        mockMvc.perform(multipart("/upload/").file(
                // FIXME 第一个参数为模拟请求表单中的参数名
                new MockMultipartFile("foo","foo.txt", MediaType.TEXT_PLAIN_VALUE,
                        "L&L".getBytes())));
    }

    @Test
    void testLoadList() throws Exception {
        mockMvc.perform(get("/upload/")).andDo(print());
    }
}