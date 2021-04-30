package com.iesvi.shared.infra.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        //TODO: Auditor cambiar por SpringSecurity
        return Optional.of("Usuario Auditoria");
    }

}