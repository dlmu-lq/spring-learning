package top.itlq.spring.boot.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.time.Instant;

/**
 * 结合spring-boot提供的jackson自动配置，添加自定义的jackson自动配置
 * @see Jackson2ObjectMapperBuilder
 * @see org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
 */
@Configuration
@ConditionalOnClass(ObjectMapper.class)
public class JacksonAutoConfiguration {

    @Configuration
    @ConditionalOnClass(Jackson2ObjectMapperBuilder.class)
    static class JacksonObjectMapperConfiguration {
        /**
         * @ConditionalOnMissingBean 使其覆盖springboot内部的且等待其他配置其build的bean加载完成才加载它；
         * @param builder
         * @return
         */
        @Bean
        @Primary
        @ConditionalOnMissingBean
        public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
            ObjectMapper re = builder.createXmlMapper(false).build();
            return re;
        }
    }


    @JsonComponent
    static class InstantJsonComponent{
        public static class InstantJsonSerializer extends JsonSerializer<Instant>{

            @Override
            public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeNumber(value.toEpochMilli());
            }
        }
        public static class InstantJsonDeserializer extends JsonDeserializer<Instant>{

            @Override
            public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
                return Instant.ofEpochMilli(p.getLongValue());
            }
        }
    }
}
