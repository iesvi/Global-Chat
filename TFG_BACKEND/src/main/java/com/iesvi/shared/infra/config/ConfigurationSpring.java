package com.iesvi.shared.infra.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.iesvi")
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "customAuditorAware")
public class ConfigurationSpring {


}