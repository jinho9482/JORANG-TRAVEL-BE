package com.example.travel_diary.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
//    @Bean
//    public OpenAPI openAPI() {
//        String jwt = "JWT";
//        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwt);
//        Components components = new Components().addSecuritySchemes(jwt, new SecurityScheme()
//                .name(jwt)
//                .type(SecurityScheme.Type.HTTP)
//                .scheme("bearer")
//                .bearerFormat("JWT")
//        );
//        return new OpenAPI()
//                .components(new Components())
//                .info(apiInfo())
//                .addSecurityItem(securityRequirement)
//                .components(components);
//    }
//    private Info apiInfo() {
//        return new Info()
//                .title("API Test") // API의 제목
//                .description("Let's practice Swagger UI") // API에 대한 설명
//                .version("1.0.0"); // API의 버전
//    }

        private final static String AUTH_HEADER = "Authorization";

        @Bean
        public OpenAPI openApi() {
            var securityScheme = new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat(AUTH_HEADER)
                    .in(SecurityScheme.In.HEADER)
                    .name(AUTH_HEADER);

            var addSecurityItem = new SecurityRequirement();
            addSecurityItem.addList(AUTH_HEADER);

            return new OpenAPI()
                    .components(new Components().addSecuritySchemes(AUTH_HEADER, securityScheme))
                    .addSecurityItem(addSecurityItem)
                    .info(apiInfo());
        }

        private Info apiInfo() {
            return new Info()
                    .title("JORANG 여행에 대한 모든 것 API")
                    .description("API 사용 메뉴얼")
                    .version("0.0.1");
        }
}