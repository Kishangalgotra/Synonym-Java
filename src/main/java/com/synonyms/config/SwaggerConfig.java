package com.synonyms.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("springshop-public").pathsToMatch("/api/**").build();
    }

    @Bean
    public OpenAPI infoAndOAuth2Security() {
        OpenAPI openAPI = new OpenAPI();
        final String securitySchemeName = "OAuth2";
        OAuthFlows oAuthFlows = new OAuthFlows();
        Scopes scopes = new Scopes();
        scopes.addString("read", "read all").addString("trust", "trust all")
                .addString("write", "write all");
        OAuthFlow password = new OAuthFlow();
        password.setScopes(scopes);
        password.setTokenUrl("/oauth/token");
        oAuthFlows.password(password);
        openAPI.addServersItem(new Server().url("http://localhost:8090"));
        openAPI.addSecurityItem(new SecurityRequirement().addList(securitySchemeName)).components(
                new Components().addSecuritySchemes(securitySchemeName, new SecurityScheme().name(securitySchemeName)
                        .type(SecurityScheme.Type.OAUTH2).scheme("oauth2").bearerFormat("JWT").flows(oAuthFlows)));
        return openAPI;
    }
}
