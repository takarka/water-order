package io.mersys.basqar.config;

import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.CLIENT_ID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final List<Parameter> listDocketParameters  = new ArrayList<>();

    public SwaggerConfig() {

        //Any parameter or header you want to require for all end_points
        Parameter oAuthHeader = new ParameterBuilder()
                .name("Authorization")
                .description("JWT Token")
                .defaultValue("Bearer ")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        listDocketParameters.add(oAuthHeader);
    }

//    @Bean
//    public Docket productApi() {
//
//        return new Docket(DocumentationType.SWAGGER_2).globalOperationParameters(listDocketParameters).select()
//                .apis(RequestHandlerSelectors.basePackage("io.mersys.basqar.web")).paths(regex("/api.*")).build();
//    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).globalOperationParameters(listDocketParameters).select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(securityScheme()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{
                new AuthorizationScope("read", "for read operations"),
                new AuthorizationScope("write", "for write operations"),
                new AuthorizationScope("email", "Access to email"),
                new AuthorizationScope("profile", "Access to profile"),
        };
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(
                        Collections.singletonList(new SecurityReference("spring_oauth", scopes())))
                .forPaths(PathSelectors.regex("/foos.*"))
                .build();
    }

//    @Bean
//    public SecurityConfiguration security() {
//        return SecurityConfigurationBuilder.builder()
//                .clientId(CLIENT_ID)
//                .clientSecret(CLIENT_SECRET)
//                .scopeSeparator(" ")
//                .useBasicAuthenticationWithAccessCodeGrant(true)
//                .build();
//    }

    private SecurityScheme securityScheme() {
        String AUTH_SERVER = "https://www.googleapis.com/oauth2/v3/token";
        GrantType grantType = new AuthorizationCodeGrantBuilder()
                .tokenEndpoint(new TokenEndpoint("https://www.googleapis.com/oauth2/v3/token", "oauthtoken"))
                .tokenRequestEndpoint(
                        new TokenRequestEndpoint("https://accounts.google.com/o/oauth2/auth", CLIENT_ID, CLIENT_ID))
                .build();

        return new OAuthBuilder().name("spring_oauth")
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
    }

}
