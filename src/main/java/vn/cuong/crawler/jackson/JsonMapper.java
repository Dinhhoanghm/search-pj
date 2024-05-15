package vn.cuong.crawler.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE;

@Configuration
public class JsonMapper {
    private static ObjectMapper objectMapper;

    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) new JsonMapper().resetJsonConfig();
        return objectMapper;
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        if (objectMapper == null) new JsonMapper().resetJsonConfig();
        return objectMapper;
    }
    public void resetJsonConfig() {
        objectMapper = new ObjectMapper();
        objectMapper
                .registerModule(new JavaTimeModule())
                .setPropertyNamingStrategy(SNAKE_CASE)
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

}