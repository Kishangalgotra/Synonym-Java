package com.synonys;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.synonys.controller", "com.synonys.entity", "com.synonys.*"})
//@EntityScan(value = "com.charge.entity")
//@EnableJpaRepositories("com.synonys.repository")
public class SpringApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }

    private static void openSwaggerApiPage() {
        String url = "http://localhost:8080/swagger-ui/index.html#/";
        String url2 = "http://localhost:8080/swagger-ui/index.html#/";
        String docUrl = "https://springdoc.org/#swagger-ui-properties";
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
                desktop.browse(new URI(docUrl));
            } catch (IOException | URISyntaxException e) {

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
