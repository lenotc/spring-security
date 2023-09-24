package com.sec.config;

import com.sec.filters.AuthenticationLoggingFilter;
import com.sec.filters.RequestValidationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class ProjectConfigV6 {
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(
                new RequestValidationFilter(),
                BasicAuthenticationFilter.class
        ).addFilterAfter(
                new AuthenticationLoggingFilter(),
                BasicAuthenticationFilter.class
        ).authorizeRequests(
                c -> c.anyRequest().permitAll()
        );

        return http.build();
    }
}
