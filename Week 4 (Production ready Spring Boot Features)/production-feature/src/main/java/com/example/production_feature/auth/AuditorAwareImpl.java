package com.example.production_feature.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //get security context
        //get authenticaltion
        //get principle
        //get username
        return Optional.of("Jayesh");
    }
}
