package com.sec.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


public class ProjectConfigV9 {

    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http.httpBasic(c -> {
            c.realmName("OTHER");
            c.authenticationEntryPoint(new CustomEntryPoint());
        });

        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
        return http.build();
    }
}
