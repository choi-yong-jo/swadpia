package kr.co.swadpia.config.doc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Configuration
@Slf4j
@OpenAPIDefinition(
        info = @Info(title = "성원 애드피아 ERP API", version = "v1",
                description = ""),
        servers = @Server(url = "/")
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SpringDocConfig {

    static {
        var schema = new Schema<>();
        schema.example(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        SpringDocUtils.getConfig().replaceWithSchema(LocalTime.class, schema);
    }




    @Bean
    public OpenApiCustomizer customOpenAPI() {
        return openApi -> {
            openApi

                    .getPaths().values().stream()
                    .flatMap(pathItem -> pathItem.readOperations().stream())
                    .forEach(operation -> {
                        operation.setOperationId(null);
                    });
        };
    }


    @Bean
    public GroupedOpenApi member() {
        return GroupedOpenApi.builder()
                .group("member")
                .pathsToMatch(
                        "/api/common/**",
                        "/api/member/**",
                        "/api/auth/**",
                        "/api/purchase/**"
                )
                .build();
    }

    @Bean
    public GroupedOpenApi test() {
        return GroupedOpenApi.builder()
                .group("test")
                .pathsToMatch(
                        "/api/member/**"
                )
                .build();
    }

    @Bean
    public GroupedOpenApi admin() {
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch(
                        "/api/admin/**"
                )
                .build();
    }

    @Bean
    public GroupedOpenApi system() {
        return GroupedOpenApi.builder()
            .group("system_v0")
            .pathsToMatch(
                "/api/v0/system/**"
            )
            .build();
    }

    @Bean
    public GroupedOpenApi common() {
        return GroupedOpenApi.builder()
            .group("common_v0")
            .pathsToMatch(
                "/api/v0/common/**"
            )
            .build();
    }

    @Bean
    public GroupedOpenApi customer() {
        return GroupedOpenApi.builder()
            .group("customer_v0")
            .pathsToMatch(
                "/api/v0/customer/**"
            )
            .build();
    }

    @Bean
    public GroupedOpenApi payroll() {
        return GroupedOpenApi.builder()
            .group("payroll_v0")
            .pathsToMatch(
                "/api/v0/payroll/**"
            )
            .build();
    }

    @Bean
    public GroupedOpenApi elastic() {
        return GroupedOpenApi.builder()
            .group("elastic")
            .pathsToMatch(
                "/api/elastic/**"
            )
            .build();
    }
}
