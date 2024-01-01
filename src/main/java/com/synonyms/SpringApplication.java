package com.synonyms;

import com.synonyms.config.CustomCacheConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@EnableScheduling
@EnableCaching
@EnableResourceServer
@EnableAuthorizationServer
@Import(CustomCacheConfig.class)
@EnableAsync(proxyTargetClass = true)
@SpringBootApplication
@EntityScan(value = "com.synonyms.entity")
@EnableJpaRepositories("com.synonyms.repository")
@ComponentScan(basePackages = {"com.synonyms.controller", "com.synonyms.entity", "com.synonyms.*"})
public class SpringApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
        //openSwaggerApiPage();
    }

    private static void openSwaggerApiPage() {
        String url = "http://localhost:8090/swagger-ui/index.html#/";
        String docUrl = "https://springdoc.org/#swagger-ui-properties";
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
                desktop.browse(new URI(docUrl));
            } catch (IOException | URISyntaxException ignore) {
            }
        } else {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
            }
        }
    }
}
