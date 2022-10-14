package com.example.chpater07.config;

import com.example.chpater07.filter.AuthenticationLoggingFilter;
import com.example.chpater07.filter.RequestValidationFilter;
import com.example.chpater07.filter.StaticKeyAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class ProjectConfig {
    public static final String READ = "READ";
    public static final String PREMIUM = "PREMIUM";

    private final StaticKeyAuthenticationFilter filter;

    @Bean
    public UserDetailsService userDetailsService() {
        var manage = new InMemoryUserDetailsManager();

        var user1 = User.withUsername("john")
                .password("12345")
                .roles(READ)
                .build();


        var user2 = User.withUsername("jane")
                .password("12345")
                .roles(READ, PREMIUM)
                .build();

        manage.createUser(user1);
        manage.createUser(user2);

        return manage;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterAt(
                        filter,
                        BasicAuthenticationFilter.class
                )
                .authorizeRequests()
                .anyRequest()
                .permitAll();

        return http.build();
    }

}
