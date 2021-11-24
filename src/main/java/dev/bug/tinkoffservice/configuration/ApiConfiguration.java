package dev.bug.tinkoffservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("api")
public class ApiConfiguration {

    private Boolean isSandBoxMode;
}
