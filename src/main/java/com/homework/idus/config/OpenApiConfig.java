package com.homework.idus.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    String appVersion = "V1";

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Idus homework API")
                .version(appVersion)
                .description("아이디어스 과제 API 문서입니다.")
                .contact(new Contact()
                        .name("최선혁")
                        .email("wafy75@gmail.com")
                );

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
