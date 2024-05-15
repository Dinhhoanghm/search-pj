package vn.cuong.crawler.data;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.crawler")
@Data
@Accessors(chain = true)
public class CrawlerRequest {
    private String url;
    private String elementClassname;
    private String elementClassname1;
    private String elementClassname2;
    private String sourcePath;
    private String fileName;

}
