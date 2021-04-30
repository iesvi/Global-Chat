package com.iesvi.app;

import com.iesvi.shared.infra.config.ConfigurationSpring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ConfigurationSpring.class)
public class AppSpringBoot extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(AppSpringBoot.class, args);
    }
}