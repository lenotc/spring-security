package com.sec.config;

import com.sec.handlers.CustomAuthenticationFailureHandler;
import com.sec.handlers.CustomAuthenticationSuccessHandler;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

public class ProjectConfigV10 {

    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler authenticationFailureHandler;

    public ProjectConfigV10(CustomAuthenticationSuccessHandler authenticationSuccessHandler, CustomAuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.formLogin(c -> c.defaultSuccessUrl("/home", true));

        http.formLogin(c ->
                c.successHandler(authenticationSuccessHandler)
                        .failureHandler(authenticationFailureHandler)
        );

        http.httpBasic(Customizer.withDefaults());

        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
        return http.build();
    }

    public UserDetailsService uds() {
        var uds = new InMemoryUserDetailsManager();

        uds.createUser(
                User.withDefaultPasswordEncoder()
                        .username("john")
                        .password("12345")
                        .authorities("read")
                        .build()
        );

        uds.createUser(
                User.withDefaultPasswordEncoder()
                        .username("bill")
                        .password("12345")
                        .authorities("write")
                        .build()
        );

        return uds;
    }
}
