package com.sec.config;

import com.sec.security.CustomAuthenticationProviderV1;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

public class ProjectConfigV1 {

    private final CustomAuthenticationProviderV1 authenticationProvider;

    public ProjectConfigV1(CustomAuthenticationProviderV1 authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    public UserDetailsService userDetailsService() {
        UserDetails user = User
                .withUsername("john")
                .password("12345")
                .authorities("read")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(withDefaults());

        http.authenticationProvider(authenticationProvider);

        http.authorizeHttpRequests(
                c -> c.anyRequest().authenticated()
        );
        return http.build();
    }

    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    // Another Away to configure UserDetailsService
    public SecurityFilterChain filterChainConfig2(HttpSecurity http) throws Exception {
        http.httpBasic(withDefaults());
        http.authorizeHttpRequests(
                c -> c.anyRequest().authenticated()
        );

        UserDetails user = User.withUsername("john").password("12345").authorities("read").build();

        UserDetailsService userDetailsService = new InMemoryUserDetailsManager(user);

        http.userDetailsService(userDetailsService);

        return http.build();
    }

    void exampleUser() {
        User.UserBuilder builder = User.withUsername("bill");

        UserDetails userDetails = builder.password("1234")
                .authorities("read", "write")
//                .passwordEncoder(p -> encode(p))
                .accountExpired(false)
                .disabled(true)
                .build();

        User.UserBuilder b2 = User.withUserDetails(userDetails);
        UserDetails u2 = b2.build();
    }
}
