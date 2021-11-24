package dev.bug.tinkoffservice.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.okhttp.OkHttpOpenApi;

@Configuration
@RequiredArgsConstructor
@EnableAsync
@EnableConfigurationProperties(ApiConfiguration.class)
public class ApplicationConfiguration {

    private final ApiConfiguration apiConfiguration;

    @Bean
    public OpenApi api() {
        String ssoToken = System.getenv("ssoToken");
        return new OkHttpOpenApi(ssoToken, apiConfiguration.getIsSandBoxMode());
    }
}
