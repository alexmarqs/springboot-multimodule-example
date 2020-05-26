package example.api.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

/**
 * The type Swagger configuration.
 */
@Configuration
@ConditionalOnProperty(name = "swagger.enabled", havingValue = "true", matchIfMissing = true)
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * Api docket.
     *
     * @return the docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("example.api.web.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .securitySchemes(Arrays.asList(basicAuthScheme()))
                .securityContexts(Arrays.asList(securityContext()))
                .apiInfo(infoApi());
    }

    /**
     * Security context security context.
     *
     * @return the security context
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(basicAuthReference()))
                .forPaths(PathSelectors.ant("/v1/customer/**"))
                .build();
    }

    /**
     * Basic auth scheme security scheme.
     *
     * @return the security scheme
     */
    private SecurityScheme basicAuthScheme() {
        return new BasicAuth("basicAuth");
    }

    /**
     * Basic auth reference security reference.
     *
     * @return the security reference
     */
    private SecurityReference basicAuthReference() {
        return new SecurityReference("basicAuth", new AuthorizationScope[0]);
    }

    /**
     * Info api.
     *
     * @return the api info
     */
    private ApiInfo infoApi() {
        return new ApiInfoBuilder()
                .title("Spring Boot Multi Module REST API")
                .description("Example API")
                .version("1.0.0")
                .build();
    }

}
