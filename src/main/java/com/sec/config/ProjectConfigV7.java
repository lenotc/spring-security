package com.sec.config;

import com.sec.filters.StaticKeyAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class ProjectConfigV7 {

    private final StaticKeyAuthenticationFilter filter;

    public ProjectConfigV7(StaticKeyAuthenticationFilter filter) {
        this.filter = filter;
    }

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.addFilterAt(filter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(c -> c.anyRequest().permitAll());

        return http.build();
    }
}
