package top.itlq.spring.boot.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JacksonAutoConfigurationTest {
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testObjectMapper() throws IOException {
        String name = "a";
        Instant birth = Instant.now();
        User user = new User();
        user.setBirth(birth);
        user.setName(name);
        String userString = objectMapper.writeValueAsString(user);
        System.out.println(userString);
        User user1 = objectMapper.readValue(userString, User.class);
        assertEquals(user.getBirth().toEpochMilli(), user1.getBirth().toEpochMilli());
        assertEquals(user.getName(), user1.getName());
    }

    static class User{
        private String name;

        private Instant birth;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Instant getBirth() {
            return birth;
        }

        public void setBirth(Instant birth) {
            this.birth = birth;
        }
    }
}