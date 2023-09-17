package com.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

public class ProjectConfigV3 {
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    private UserDetailsService searchBySQL(DataSource ds) {
        var sqlUsers = "SELECT username, password, enabled FROM  users WHERE username = ?";
        var sqlAuthorities = "SELECT username, authority FROM authorities WHERE username = ?";
        var userDetailManager = new JdbcUserDetailsManager(ds);
        userDetailManager.setUsersByUsernameQuery(sqlUsers);
        userDetailManager.setAuthoritiesByUsernameQuery(sqlAuthorities);

        return userDetailManager;
    }
}
