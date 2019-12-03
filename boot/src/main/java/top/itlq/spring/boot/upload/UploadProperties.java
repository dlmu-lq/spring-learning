package top.itlq.spring.boot.upload;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("itlq")
public class UploadProperties {
    private String uploadLocation;

    public String getUploadLocation() {
        return uploadLocation;
    }

    public void setUploadLocation(String uploadLocation) {
        this.uploadLocation = uploadLocation;
    }
}
